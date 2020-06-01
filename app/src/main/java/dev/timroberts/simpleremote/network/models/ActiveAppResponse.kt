package dev.timroberts.simpleremote.network.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "active-app")
data class ActiveAppResponse(
    @Element val app: AppXml,
    @Element val screensaver: ScreensaverXml?
)