package `in`.byval.treats.Login

import `in`.byval.assist.UtilFunctions.UtilFunctions.appendLog
import `in`.byval.treats.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockListener
import com.andrognito.pinlockview.PinLockView
import com.androidstudy.networkmanager.Tovuti
import java.util.*


class PinEntryActivity : AppCompatActivity() {
    val TAG = "PinLockView"
    private var mPinLockView: PinLockView? = null
    private var mIndicatorDots: IndicatorDots? = null
    var mContext: Context? = null
    var mPIN = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_entry)
       // requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mContext = this@PinEntryActivity
        Objects.requireNonNull(supportActionBar)!!.hide()

        mPinLockView = findViewById<View>(R.id.pin_lock_view) as PinLockView
        mIndicatorDots = findViewById<View>(R.id.indicator_dots) as IndicatorDots

        mPinLockView!!.setPinLockListener(mPinLockListener)
        mPinLockView!!.attachIndicatorDots(mIndicatorDots)
        mPinLockView!!.pinLength = 4

        // mPIN= UtilFunctions.getPreference(mContext as PinEntryActivity, UtilFunctions.PREFS_APP_DEVICE_PIN).toString()

        mPIN="1111"

        Tovuti.from(this).monitor { connectionType, isConnected, isFast ->
            IsNetworkConected(isConnected)
        }
    }


    private val mPinLockListener: PinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {

            if (pin.equals(mPIN, ignoreCase = true)) {
                try {

                    val intent2 = Intent(mContext, LoginActivity::class.java)
                    mContext!!.startActivity(intent2)
                    (mContext as Activity).finish()

                } catch (e: Exception) {
                    appendLog(e)
                    e.printStackTrace()
                }
            }else{

                showToast(getString(R.string.Invalid_entry))
            }
        }

        override fun onEmpty() {
            Log.d(TAG, "Pin empty")
        }

        override fun onPinChange(pinLength: Int, intermediatePin: String) {
            Log.d(TAG, "Pin changed, new length $pinLength with intermediate pin $intermediatePin")
        }
    }

    private fun IsNetworkConected(connected: Boolean) {
        if(connected){
            //NetworkConnected
        }else{
            //NetworkDisconnected
        }
    }

    private fun showToast(message: String?){
        Toast.makeText(this@PinEntryActivity,message,Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        Tovuti.from(this).stop()
        super.onStop()
    }
}