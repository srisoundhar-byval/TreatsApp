package `in`.byval.treats.Login

import `in`.byval.assist.UtilFunctions.UtilFunctions
import `in`.byval.treats.Dashboard.DashboardTabActivity
import `in`.byval.treats.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.networkmanager.Tovuti
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Objects.requireNonNull(supportActionBar)!!.hide()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        mContext = this@LoginActivity


        card_circleView.setBackgroundResource(R.drawable.card_view_bg);


        val actual_color = resources.getColor(R.color.colorPrimaryDark)
        val lighter_color = lightenColor(resources.getColor(R.color.colorPrimaryDark))
        val gradient_color = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(actual_color, lighter_color)
        )


        layout_background.background = gradient_color

        /*  val StringJson = JWTUtils.decoded("Token")
          val jsonObject = JSONObject(StringJson)*/

        edtTxt_Username.setText("test")
        edtTxt_password.setText("test")

        mLoginButton.setOnClickListener {
            val isValidate: Boolean = checkFields()
            if (isValidate) {
                if (UtilFunctions.isNetworkAvailable(mContext as LoginActivity)) {
                    var mUserName = edtTxt_Username.text.toString().trim { it <= ' ' }
                    var mPassword = edtTxt_password.text.toString().trim { it <= ' ' }
                    //  loginServiceCall(mUserName, mPassword)

                    val intent = Intent(mContext, DashboardTabActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    (mContext as LoginActivity).startActivity(intent)
                    (mContext as LoginActivity).finish()


                } else {
                    Toast.makeText(mContext, UtilFunctions.NETWORK_UNAVAILABLE, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }











        Tovuti.from(this).monitor { connectionType, isConnected, isFast ->
            IsNetworkConected(isConnected)
        }
    }

    @ColorInt
    fun lightenColor(@ColorInt color: Int): Int {
        return Color.HSVToColor(FloatArray(3).apply {
            Color.colorToHSV(color, this)
            this[2] *= 1.5f
        })
    }

    private fun IsNetworkConected(connected: Boolean) {
        if (connected) {
            //NetworkConnected
        } else {
            //NetworkDisconnected
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        Tovuti.from(this).stop()
        super.onStop()
    }

    private fun checkFields(): Boolean {
        edtTxt_Username_layout.error = null
        edtTxt_password_layout.error = null
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]+"
        if (edtTxt_Username.text.isEmpty()) {
            edtTxt_Username_layout.error = resources.getString(R.string.Invalid_entry)
            return false
        }

        if (edtTxt_password.text.isEmpty()) {
            edtTxt_password_layout.setError(resources.getString(R.string.Invalid_entry))
            return false
        }
        return true
    }


}