package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GeneralMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        getMusicSource(url)?.let { musicSource ->
            when (musicSource) {
                MusicSource.MUZIC_IR -> {
                    MuzicIrMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                        close()
                    }
                }

                MusicSource.TARAFDARI -> {
                    TarafdariMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                        close()
                    }
                }

                MusicSource.AVAZINO -> {
                    AvazinoMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                        close()
                    }
                }
            }
        } ?: run {
            close()
        }

        awaitClose()
    }

    private fun getMusicSource(url: String): MusicSource? = when {
        url.startsWith(MusicSource.TARAFDARI.website) -> MusicSource.TARAFDARI
        url.startsWith(MusicSource.MUZIC_IR.website) -> MusicSource.MUZIC_IR
        url.startsWith(MusicSource.AVAZINO.website) -> MusicSource.AVAZINO
        else -> null
    }
}