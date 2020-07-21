package `in`.byval.treats.Dashboard

import `in`.byval.treats.Dashboard.ui.main.SectionsPagerAdapter
import `in`.byval.treats.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.androidstudy.networkmanager.Tovuti
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.activity_dashboard_tab.*

class DashboardTabActivity : AppCompatActivity() {
    var mContext: Context? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_tab)

      //  val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
       // viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        //tabs.setupWithViewPager(viewPager)
        //Always display
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        mContext = this@DashboardTabActivity
        val actual_color = resources.getColor(R.color.colorPrimaryDark)
        val lighter_color = lightenColor(resources.getColor(R.color.colorPrimaryDark))
        val gradient_color = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(actual_color, lighter_color)
        )
        top_bar_lay.background = gradient_color
        try {

            tabs.addTab(tabs.newTab().setText("Dashboard"));
            tabs.addTab(tabs.newTab().setText("Pending"));
            tabs.addTab(tabs.newTab().setText("Processing"));
            tabs.addTab(tabs.newTab().setText("Completed"));
           // tabs.setTabGravity(TabLayout.GRAVITY_FILL);


            val adapter =
                SectionsPagerAdapter(this, supportFragmentManager, tabs!!.tabCount)
            viewPager.adapter = adapter

            viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))

            tabs!!.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })


        } catch (e: Exception) {
            showToast(e.message)
        }

        Tovuti.from(this).monitor { connectionType, isConnected, isFast ->
            IsNetworkConected(isConnected)
        }

    }


    private fun IsNetworkConected(connected: Boolean) {
        if (connected) {
            //NetworkConnected
        } else {
            //NetworkDisconnected
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this@DashboardTabActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        Tovuti.from(this).stop()
        super.onStop()
    }

    @ColorInt
    fun lightenColor(@ColorInt color: Int): Int {
        return Color.HSVToColor(FloatArray(3).apply {
            Color.colorToHSV(color, this)
            this[2] *= 1.5f
        })
    }
}