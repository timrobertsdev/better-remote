package us.timandkarli.betterrokuremote.presentation.channelview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import us.timandkarli.betterrokuremote.R

class ChannelFragment : Fragment() {

    companion object {
        fun newInstance() = ChannelFragment()
    }

    private lateinit var viewModel: ChannelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.channel_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChannelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
