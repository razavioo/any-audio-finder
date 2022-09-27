package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MuzicIrMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document: Document = Jsoup.connect(url).get()
        val element: Element? = document.select("div").firstOrNull { element -> element.hasClass("audio") }
        val audioElement = element?.select("audio")?.firstOrNull()
        val downloadUrl = audioElement?.select("source")?.firstOrNull()?.attr("src")
        if (downloadUrl != null) {
            val musicInformation = MusicInformation(
                source = MusicSource.MUZICIR,
                pageUrl = url,
                downloadUrl = downloadUrl
            )
            trySend(musicInformation)
        }

        close()
        awaitClose()
    }
}