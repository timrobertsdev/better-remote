package us.timandkarli.betterrokuremote.presentation

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import org.koin.android.viewmodel.ext.android.viewModel
import us.timandkarli.betterrokuremote.R

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeViewState()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            val intent = Intent("KEYPRESS").apply { putExtra("KEY_CODE", keyCode) }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun subscribeViewState() {
        mainViewModel.viewState.observe(this, Observer {
            when (it) {
                is MainViewState.DeviceDisconnected ->
                    // TODO: Add snackbar notification
                    findNavController(R.id.nav_host_fragment).navigate(R.id.global_launch_discover)
                is MainViewState.NoDeviceSelected ->
                    findNavController(R.id.nav_host_fragment).navigate(R.id.global_launch_discover)
            }
        })
    }
}