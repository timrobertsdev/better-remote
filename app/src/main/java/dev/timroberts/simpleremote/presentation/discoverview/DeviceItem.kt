package dev.timroberts.simpleremote.presentation.discoverview

import android.os.Build
import android.text.Html
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import dev.timroberts.simpleremote.R
import dev.timroberts.simpleremote.models.RokuDevice
import kotlinx.android.synthetic.main.discovered_device_card.view.*
import java.lang.ref.WeakReference

class DeviceItem(val device: RokuDevice,
                 private val listener: WeakReference<OnDeviceItemClickedListener>) : Item() {
    override fun getLayout(): Int = R.layout.discovered_device_card

    @Suppress("DEPRECATION")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.itemView.device_name.text = Html.fromHtml(device.friendlyDeviceName, Html.FROM_HTML_MODE_LEGACY)
        } else {

            viewHolder.itemView.device_name.text = Html.fromHtml(device.friendlyDeviceName)
        }

        viewHolder.itemView.device_location.text = device.location

        viewHolder.root.setOnClickListener { listener.get()?.onDeviceItemClicked(this@DeviceItem) }
    }
}

interface OnDeviceItemClickedListener {
    fun onDeviceItemClicked(item: DeviceItem)
}