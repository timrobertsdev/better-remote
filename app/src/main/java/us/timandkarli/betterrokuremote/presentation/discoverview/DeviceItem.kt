package us.timandkarli.betterrokuremote.presentation.discoverview

import android.os.Build
import android.text.Html
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.discovered_device_card.view.*
import us.timandkarli.betterrokuremote.R
import us.timandkarli.betterrokuremote.models.RokuDevice
import java.lang.ref.WeakReference

class DeviceItem(val device: RokuDevice,
                 private val listener: WeakReference<OnDeviceItemClickedListener>) : Item() {
    override fun getLayout(): Int = R.layout.discovered_device_card

    @Suppress("DEPRECATION")
    override fun bind(viewHolder: ViewHolder, position: Int) {
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