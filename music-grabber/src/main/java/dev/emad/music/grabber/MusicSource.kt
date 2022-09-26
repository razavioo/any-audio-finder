package dev.emad.music.grabber

sealed class MusicSource(val website: String) {
    object TARAFDARI : MusicSource("https://www.tarafdari.com")
    object MUZICIR : MusicSource("https://muzicir.com")
}