package data

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int?,
    val task: String,
    var completed: Boolean
)