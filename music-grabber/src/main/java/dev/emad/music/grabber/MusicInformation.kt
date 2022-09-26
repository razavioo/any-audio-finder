package dev.emad.music.grabber

data class MusicInformation<T : MusicSource>(
    val source: T,
    val pageUrl: String,
    val downloadUrl: String
)