package us.timandkarli.simpleremote.network.models

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "screensaver")
data class ScreensaverXml(
    @Attribute(name = "id") val id: String,
    @Attribute(name = "type") val type: String,
    @Attribute(name = "subtype") val subtype: String?,
    @Attribute(name = "version") val version: String,
    @TextContent val name: String
)