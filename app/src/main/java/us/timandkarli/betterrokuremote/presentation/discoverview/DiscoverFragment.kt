package us.timandkarli.betterrokuremote.presentation.discoverview

import android.content.res.ColorStateList
import android.graphics.Color
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
import kotlinx.android.synthetic.main.discovered_device_card.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import us.timandkarli.betterrokuremote.R
import us.timandkarli.betterrokuremote.presentation.MainViewModel

class DiscoverFragment : Fragment() {
    private val discoverViewModel: DiscoverViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val deviceListAdapter = GroupAdapter<ViewHolder>()
    private val deviceItemList = mutableListOf<DeviceItem>()

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
            Timber.d("ViewState changed: ${viewState.state.name}")

            if (viewState.data != null) {
                val device = viewState.data
                if (!deviceItemList.any { it.device.location == device.location })
                    deviceItemList.add(DeviceItem(device, this::navigateBack))

                deviceListAdapter.updateAsync(deviceItemList)
            }

            when (viewState.state) {
                DiscoverState.DISCOVERING -> {
                    discover_fab.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                    discover_fab.setImageResource(R.drawable.cancel)
                    discover_fab.setOnClickListener { discoverViewModel.cancelDiscover() }
                }
                else -> {
                    discover_fab.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    discover_fab.setImageResource(R.drawable.search)
                    discover_fab.setOnClickListener { discoverViewModel.startDiscover() }
                }
            }
        })
    }

    private fun navigateBack(view: View) {
        discoverViewModel.cancelDiscover()
        discoverViewModel.setHost(view.device_location.text.toString())
        findNavController().popBackStack()
    }
}
