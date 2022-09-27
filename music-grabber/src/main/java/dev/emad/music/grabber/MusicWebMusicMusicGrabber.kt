package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MusicWebMusicMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document = Jsoup.connect(url).get()
        val element = document.select("div").firstOrNull { element -> element.hasClass("musiclinks") }
        val audioElement = element?.select("audio")?.firstOrNull()
        val sourceElement = audioElement?.select("source")?.firstOrNull()
        val downloadUrl = sourceElement?.attr("src")
        if (downloadUrl != null) {
            val musicInformation = MusicInformation(
                source = MusicSource.MUSIC_WEB,
                pageUrl = url,
                downloadUrl = downloadUrl
            )
            trySend(musicInformation)
        }

        close()
        awaitClose()
    }
}