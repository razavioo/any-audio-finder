package dev.emad.music.grabber

enum class MusicSource(val website: String, val grabber: MusicGrabber) {
    TARAFDARI("https://www.tarafdari.com", TarafdariMusicGrabber()),
    MUZIC_IR("https://muzicir.com", MuzicIrMusicGrabber()),
    AVAZINO("https://avazino.net", AvazinoMusicGrabber()),
    MUSIC_FA("https://music-fa.com", MusicFaMusicGrabber()),
    MUSIC_FEED("https://musicfeed.ir", MusicFeedMusicGrabber()),
    MAHAN_MUSIC("https://mahanmusic.net", MahanMusicMusicGrabber()),
    MUSIC_DAYS("https://www.musicdays.ir", MusicDaysMusicGrabber()),
    MUSIC_DEL("https://musicdel.ir", MusicDelMusicGrabber()),
    PLAY_MUSIC("https://www.playmusic.ir", PlayMusicMusicGrabber()),
    MUSIC_WEB("https://musicsweb.ir", MusicWebMusicMusicGrabber()),
    BEAUTY_MUSIC("http://www.beautymusic.ir", BeautyMusicMusicMusicGrabber())
}