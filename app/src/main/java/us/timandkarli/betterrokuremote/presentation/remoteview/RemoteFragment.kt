package us.timandkarli.betterrokuremote.presentation.remoteview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.get
import us.timandkarli.betterrokuremote.R
import us.timandkarli.betterrokuremote.network.HostSelectionInterceptor

class RemoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.remote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val hostSelectionInterceptor = get<HostSelectionInterceptor>()
        if (hostSelectionInterceptor.host == null) {
            val action = RemoteFragmentDirections.remoteToDiscover()
            findNavController().navigate(action)
        }
    }

}
