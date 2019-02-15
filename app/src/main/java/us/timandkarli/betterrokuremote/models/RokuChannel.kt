package us.timandkarli.betterrokuremote.models

data class RokuChannel(
    val id: String,
    val name: String,
    val type: String,
    val subtype: String?,
    val version: String
)