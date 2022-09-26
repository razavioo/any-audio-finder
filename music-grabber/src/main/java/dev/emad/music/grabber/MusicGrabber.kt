package dev.emad.music.grabber

import kotlinx.coroutines.flow.Flow

abstract class MusicGrabber {
    abstract fun grab(url: String): Flow<MusicInformation>
}