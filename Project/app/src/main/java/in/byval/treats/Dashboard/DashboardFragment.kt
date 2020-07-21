package `in`.byval.treats.Dashboard

import `in`.byval.treats.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.androidstudy.networkmanager.Tovuti

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        try {






        } catch (e: Exception) {
            showToast(e.message)
        }

        Tovuti.from(activity).monitor { connectionType, isConnected, isFast ->
            IsNetworkConected(isConnected)
        }
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
    private fun IsNetworkConected(connected: Boolean) {
        if (connected) {
            //NetworkConnected
        } else {
            //NetworkDisconnected
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
    override fun onStop() {
        Tovuti.from(activity).stop()
        super.onStop()
    }
}