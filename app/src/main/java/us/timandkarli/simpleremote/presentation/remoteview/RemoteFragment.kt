package us.timandkarli.simpleremote.presentation.remoteview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.remote_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import us.timandkarli.simpleremote.R

class RemoteFragment : Fragment() {
    private val remoteViewModel: RemoteViewModel by viewModel()

    private val volumeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val keyCode = intent?.getIntExtra("KEY_CODE", 0)
            when (keyCode) {
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    remoteViewModel.volumeDown()
                }
                KeyEvent.KEYCODE_VOLUME_UP -> {
                    remoteViewModel.volumeUp()
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
        subscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(context!!).unregisterReceiver(volumeReceiver)
    }

    private fun subscribeUI() {
        vol_down.setOnClickListener { remoteViewModel.volumeDown() }
        vol_up.setOnClickListener { remoteViewModel.volumeUp() }
        move_down.setOnClickListener { remoteViewModel.moveDown() }
        move_up.setOnClickListener { remoteViewModel.moveUp() }
        move_left.setOnClickListener { remoteViewModel.moveLeft() }
        move_right.setOnClickListener { remoteViewModel.moveRight() }
        okay.setOnClickListener { remoteViewModel.okay() }
        power.setOnClickListener { remoteViewModel.powerOff() }
        home.setOnClickListener { remoteViewModel.goHome() }
        back.setOnClickListener { remoteViewModel.goBack() }
        play_pause.setOnClickListener { remoteViewModel.playPause() }
        rewind.setOnClickListener { remoteViewModel.rewind() }
        fast_forward.setOnClickListener { remoteViewModel.fastForward() }
    }
}
