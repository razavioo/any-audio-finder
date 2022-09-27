package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GeneralMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        getMusicSource(url)?.let { musicSource ->
            when (musicSource) {
                MusicSource.MUZICIR -> {
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

                MusicSource.MUSICFA -> {
                    MusicfaMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                        close()
                    }
                }

                MusicSource.MUSIC_FEED -> {
                    MusicFeedMusicGrabber().grab(url).collectLatest {
                        trySend(it)
                        close()
                    }
                }

                MusicSource.MAHAN_MUSIC -> {
                    MahanMusicMusicGrabber().grab(url).collectLatest {
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
        url.startsWith(MusicSource.MUZICIR.website) -> MusicSource.MUZICIR
        url.startsWith(MusicSource.AVAZINO.website) -> MusicSource.AVAZINO
        url.startsWith(MusicSource.MUSICFA.website) -> MusicSource.MUSICFA
        url.startsWith(MusicSource.MUSIC_FEED.website) -> MusicSource.MUSIC_FEED
        url.startsWith(MusicSource.MAHAN_MUSIC.website) -> MusicSource.MAHAN_MUSIC
        else -> null
    }
}