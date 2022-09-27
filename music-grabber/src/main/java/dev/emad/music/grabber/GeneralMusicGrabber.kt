package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GeneralMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        getMusicSource(url)?.let { musicSource ->
            getMusicGrabber(musicSource).grab(url).collectLatest {
                trySend(it)
                close()
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
        url.startsWith(MusicSource.MUSIC_FA.website) -> MusicSource.MUSIC_FA
        url.startsWith(MusicSource.MUSIC_FEED.website) -> MusicSource.MUSIC_FEED
        url.startsWith(MusicSource.MAHAN_MUSIC.website) -> MusicSource.MAHAN_MUSIC
        url.startsWith(MusicSource.MUSIC_DAYS.website) -> MusicSource.MUSIC_DAYS
        url.startsWith(MusicSource.MUSIC_DEL.website) -> MusicSource.MUSIC_DEL
        url.startsWith(MusicSource.PLAY_MUSIC.website) -> MusicSource.PLAY_MUSIC
        else -> null
    }

    private fun getMusicGrabber(musicSource: MusicSource): MusicGrabber = when (musicSource) {
        MusicSource.MUZIC_IR -> MuzicIrMusicGrabber()
        MusicSource.TARAFDARI -> TarafdariMusicGrabber()
        MusicSource.AVAZINO -> AvazinoMusicGrabber()
        MusicSource.MUSIC_FA -> MusicFaMusicGrabber()
        MusicSource.MUSIC_FEED -> MusicFeedMusicGrabber()
        MusicSource.MAHAN_MUSIC -> MahanMusicMusicGrabber()
        MusicSource.MUSIC_DAYS -> MusicDaysMusicGrabber()
        MusicSource.MUSIC_DEL -> MusicDelMusicGrabber()
        MusicSource.PLAY_MUSIC -> PlayMusicMusicGrabber()
    }
}