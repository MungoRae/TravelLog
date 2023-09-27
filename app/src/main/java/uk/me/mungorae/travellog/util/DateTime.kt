package uk.me.mungorae.travellog.util


interface DateTime {

    override fun toString(): String

    fun toLongDateString(): String

    fun year(): Int

    fun month(): Int

    fun dayOfMonth(): Int

}