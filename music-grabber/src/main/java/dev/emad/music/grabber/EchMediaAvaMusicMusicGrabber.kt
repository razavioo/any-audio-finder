package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup

class EchMediaAvaMusicMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document = Jsoup.connect(url).get()
        val element = document.select("div").firstOrNull { element -> element.hasClass("dlboxes") }
        val audioElement = element?.select("a")?.firstOrNull()
        val downloadUrl = audioElement?.attr("href")
        if (downloadUrl != null) {
            val musicInformation = MusicInformation(
                source = MusicSource.ECH_MEDIA,
                pageUrl = url,
                downloadUrl = downloadUrl
            )
            trySend(musicInformation)
        }

        close()
        awaitClose()
    }
}