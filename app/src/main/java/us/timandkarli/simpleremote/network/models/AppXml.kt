package us.timandkarli.simpleremote.network.models

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "app")
data class AppXml(
    @Attribute(name = "id") val id: String,
    @Attribute(name = "subtype") val subtype: String?,
    @Attribute(name = "type") val type: String,
    @Attribute(name = "version") val version: String,
    @TextContent val name: String
)