package dev.emad.business.model

import java.util.*

data class Audio(
    var id: Long? = null,
    var pageUrl: String,
    var downloadUrl: String,
    var redirectionId: String,
    var createdAt: Date
)