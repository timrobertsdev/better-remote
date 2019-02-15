package us.timandkarli.betterrokuremote.presentation.discoverview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.discover_fragment.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import us.timandkarli.betterrokuremote.R
import us.timandkarli.betterrokuremote.presentation.MainViewModel
import java.lang.ref.WeakReference

class DiscoverFragment : Fragment(), OnDeviceItemClickedListener {
    private val discoverViewModel: DiscoverViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private val deviceListAdapter = GroupAdapter<ViewHolder>()
    private val deviceItemList = mutableListOf<DeviceItem>()

    private var multicastLock: WifiManager.MulticastLock? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Attempting to acquire MulticastLock")
        val wifiManager = context!!.applicationContext!!.getSystemService(Context.WIFI_SERVICE) as WifiManager
        multicastLock = wifiManager.createMulticastLock("multicastLock")
        multicastLock?.apply {
            setReferenceCounted(true)
            acquire()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.discover_fragment, container, false)

        view.device_list.layoutManager = LinearLayoutManager(context)
        view.device_list.adapter = deviceListAdapter

        view.discover_fab.setOnClickListener { discoverViewModel.startDiscover() }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discoverViewModel.viewState.observe(this, Observer { viewState ->
            Timber.d("ViewState changed: $viewState")

            when (viewState) {
                is DiscoverViewState.Discovering -> {
                    discover_fab.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                    discover_fab.setImageResource(R.drawable.cancel)
                    discover_fab.setOnClickListener { discoverViewModel.cancelDiscover() }
                }
                is DiscoverViewState.Discovered -> {
                    val device = viewState.rokuDevice
                    if (!deviceItemList.any { it.device.location == device.location })
                        deviceItemList.add(DeviceItem(device, WeakReference(this)))
                    deviceListAdapter.updateAsync(deviceItemList)
                }
                else -> {
                    discover_fab.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    discover_fab.setImageResource(R.drawable.search)
                    discover_fab.setOnClickListener { discoverViewModel.startDiscover() }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        multicastLock?.release()
    }

    override fun onDeviceItemClicked(item: DeviceItem) {
        discoverViewModel.cancelDiscover()
        mainViewModel.setCurrentDevice(item.device)
        findNavController().popBackStack()
    }
}
