package uk.me.mungorae.travellog.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class JavaTimeDateTime(private val javaDateTime: LocalDateTime) : DateTime {
    override fun toString(): String {
        return DateTimeFormatter.ISO_DATE_TIME.format(javaDateTime)
    }

    override fun toLongDateString(): String {
        return DateTimeFormatter.ofPattern("dd MMMM yyyy").format(javaDateTime)
    }

    override fun year(): Int = javaDateTime.year

    override fun month(): Int = javaDateTime.monthValue

    override fun dayOfMonth(): Int = javaDateTime.dayOfMonth
}
