package dev.emad.music.grabber

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest

class GeneralMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = callbackFlow {
        getMusicSource(url)?.let { musicSource ->
            when (musicSource) {
                MusicSource.MUZICIR -> {
                    MuzicIrMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                    }
                }

                MusicSource.TARAFDARI -> {
                    TarafdariMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                    }
                }
            }
        }
    }

    private fun getMusicSource(url: String): MusicSource? {
        val source: MusicSource? = when {
            url.startsWith(MusicSource.TARAFDARI.website) -> {
                MusicSource.TARAFDARI
            }

            url.startsWith(MusicSource.MUZICIR.website) -> {
                MusicSource.MUZICIR
            }

            else -> null
        }
        return source
    }
}