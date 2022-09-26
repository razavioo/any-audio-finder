package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MusicFeedMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document: Document = Jsoup.connect(url).get()
        val element: Element? = document.select("div").firstOrNull { element -> element.hasClass("fa_dlbox") }
        val audioElement = element?.select("audio")?.firstOrNull()
        val downloadUrl = audioElement?.attr("src")
        val musicInformation = MusicInformation(
            source = MusicSource.MUSIC_FEED,
            pageUrl = url,
            downloadUrl = downloadUrl.toString()
        )
        trySend(musicInformation)
        close()

        awaitClose()
    }
}