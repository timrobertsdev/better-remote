package us.timandkarli.betterrokuremote.presentation.remoteview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.discovered_device_card.*
import kotlinx.android.synthetic.main.remote_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import us.timandkarli.betterrokuremote.R
import us.timandkarli.betterrokuremote.presentation.CurrentDeviceState
import us.timandkarli.betterrokuremote.presentation.MainViewModel

class RemoteFragment : Fragment() {
    companion object {
        // TODO: Change to configurable setting
        private const val VOLUME_STEP = 5
    }
    private val remoteViewModel: RemoteViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private val volumeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val keyCode = intent?.getIntExtra("KEY_CODE", 0)
            when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    remoteViewModel.volumeDown(VOLUME_STEP)
                }
                KeyEvent.KEYCODE_VOLUME_UP -> {
                    remoteViewModel.volumeUp(VOLUME_STEP)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val filter = IntentFilter().apply { addAction("KEYPRESS") }
        LocalBroadcastManager.getInstance(context!!).registerReceiver(volumeReceiver, filter)
        return inflater.inflate(R.layout.remote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeCurrentDevice()
        subscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(context!!).unregisterReceiver(volumeReceiver)
    }

    @Suppress("DEPRECATION")
    fun subscribeCurrentDevice() {
        mainViewModel.currentDevice.observe(this, Observer {
            when (it) {
                is CurrentDeviceState.NoDeviceSelected -> launchDiscover()
                is CurrentDeviceState.DeviceSelected -> {
                    val device = it.rokuDevice
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        device_name.text = Html.fromHtml(device.friendlyDeviceName, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        device_name.text = Html.fromHtml(device.friendlyDeviceName)
                    }
                    device_location.text = device.location
                }
            }
        })
    }

    fun subscribeUI() {
        vol_down.setOnClickListener { remoteViewModel.volumeDown(VOLUME_STEP) }
        vol_up.setOnClickListener { remoteViewModel.volumeUp(VOLUME_STEP) }
        move_down.setOnClickListener { remoteViewModel.moveDown() }
        move_up.setOnClickListener { remoteViewModel.moveUp() }
        move_left.setOnClickListener { remoteViewModel.moveLeft() }
        move_right.setOnClickListener { remoteViewModel.moveRight() }
        okay.setOnClickListener { remoteViewModel.okay() }
    }

    fun launchDiscover() {
        val action = RemoteFragmentDirections.remoteToDiscover()
        findNavController().navigate(action)
    }
}
