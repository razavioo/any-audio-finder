package dev.emad.music.grabber

data class MusicInformation(
    val source: MusicSource,
    val pageUrl: String,
    val downloadUrl: String
)