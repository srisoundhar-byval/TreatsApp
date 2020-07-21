package `in`.byval.treats.Login

import `in`.byval.assist.UtilFunctions.UtilFunctions
import `in`.byval.treats.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private var mContext: Context? = null
    var splashTread: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mContext = this@SplashScreenActivity
        Objects.requireNonNull(supportActionBar)!!.hide()

        try {

            splashTread = object : Thread() {
                override fun run() {
                    var waited = 0
                    while (waited < 2000) {
                        sleep(100)
                        waited += 100
                    }


                    val intent = Intent(mContext, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    (mContext as SplashScreenActivity).startActivity(intent)
                    (mContext as SplashScreenActivity).finish()


                }

            }
            (splashTread as Thread).start()


        } catch (e: Exception) {
            showToast(e.message)


        }

    }
    override fun onResume() {
        super.onResume()
        val mAPP_THEME: String? =   UtilFunctions.getPreference(this@SplashScreenActivity, UtilFunctions.PREFS_APP_THEME)
        if (mAPP_THEME == UtilFunctions.DARK) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun showToast(message: String?){
        Toast.makeText(this@SplashScreenActivity,message,Toast.LENGTH_LONG).show()
    }
}