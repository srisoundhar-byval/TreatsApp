package `in`.byval.treats.Dashboard.ui.main

import `in`.byval.treats.Completed.CompletedFragment
import `in`.byval.treats.Dashboard.DashboardFragment
import `in`.byval.treats.Pending.PendingFragment
import `in`.byval.treats.Processing.ProcessingFragment
import `in`.byval.treats.R
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    var totalTab = totalTabs

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                DashboardFragment()
            }
            1 -> {
                PendingFragment()
            }
            2 -> {
                ProcessingFragment()
            }
            3 -> {
                CompletedFragment()
            }

            else -> DashboardFragment()
        }
    }

    /*override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }*/

    override fun getCount(): Int {
        return totalTab
    }
}