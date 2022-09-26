package dev.emad.music.grabber

import kotlinx.coroutines.flow.Flow

abstract class MusicGrabber<T : MusicSource> {
    abstract fun grab(url: String): Flow<MusicInformation<T>>
}