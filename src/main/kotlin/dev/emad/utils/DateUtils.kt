package dev.emad.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object DateUtils {
    fun LocalDateTime.toDate(): Date {
        return Date.from(atZone(ZoneId.systemDefault()).toInstant())
    }

    fun Date.toLocalDateTime(): LocalDateTime {
        return LocalDateTime.ofInstant(toInstant(), ZoneId.systemDefault())
    }
}