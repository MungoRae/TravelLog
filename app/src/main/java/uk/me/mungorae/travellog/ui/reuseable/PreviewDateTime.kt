package uk.me.mungorae.travellog.ui.reuseable

import uk.me.mungorae.travellog.util.DateTime

class PreviewDateTime : DateTime {
    override fun toString(): String = "2011/01/01T01:01:01:000Z"

    override fun toLongDateString() = "01 January 2011"

    override fun year() = 2011

    override fun month() = 1

    override fun dayOfMonth() = 1
}
