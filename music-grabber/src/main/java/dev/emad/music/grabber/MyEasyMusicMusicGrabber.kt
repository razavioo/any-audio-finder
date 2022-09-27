package dev.emad.music.grabber

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.jsoup.Jsoup

class MyEasyMusicMusicGrabber : MusicGrabber() {
    override fun grab(url: String): Flow<MusicInformation> = channelFlow {
        val document = Jsoup.connect(url).get()
        val element = document.select("div").firstOrNull { element -> element.hasClass("boxdownload") }
        val audioElement = element?.select("audio")?.firstOrNull()
        val downloadUrl = audioElement?.attr("src")
        if (downloadUrl != null) {
            val musicInformation = MusicInformation(
                source = MusicSource.MY_EASY_MUSIC,
                pageUrl = url,
                downloadUrl = downloadUrl
            )
            trySend(musicInformation)

        }

        close()
        awaitClose()
    }
}