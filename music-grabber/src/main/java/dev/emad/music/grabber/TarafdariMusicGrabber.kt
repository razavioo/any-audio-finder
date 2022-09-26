package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class TarafdariMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document: Document = Jsoup.connect(url).get()
        val links = document.select("a")
        for (element in links) {
            if (element.hasClass("download")) {
                val link = element.attr("href")
                if (link.endsWith(".mp3")) {
                    val musicInformation = MusicInformation(
                        source = MusicSource.TARAFDARI,
                        pageUrl = url,
                        downloadUrl = link
                    )
                    trySend(musicInformation)
                    close()
                }
            }
        }
        close()

        awaitClose()
    }
}