package uk.me.mungorae.travellog.util

import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

interface DateTimeProvider {
    fun today(): DateTime

    fun createDateTimeFromYearMonthDay(year: Int, month: Int, dayOfMonth: Int): DateTime {
        return JavaTimeDateTime(LocalDate.of(year, month, dayOfMonth).atTime(0,0))
    }

    class Impl @Inject constructor(private val clock: Clock): DateTimeProvider {
        override fun today(): DateTime {
            return JavaTimeDateTime(LocalDateTime.now(clock))
        }
    }
}