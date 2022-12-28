package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GeneralMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        getMusicSource(url)?.let { musicSource ->
            try {
                musicSource.grabber.grab(url).collectLatest {
                    trySend(it)
                    close()
                }
            } catch (exception: Exception) {
                close()
            }
        } ?: run {
            close()
        }

        awaitClose()
    }

    private fun getMusicSource(url: String): MusicSource? {
        return MusicSource.values().firstOrNull { url.startsWith(it.website) }
    }
}