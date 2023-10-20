package uk.me.mungorae.travellog.data

import uk.me.mungorae.travellog.util.DateTime

data class Travel(
    val name: String,
    val description: String,
    val date: DateTime,
    val imageUrls: List<String>,
)
