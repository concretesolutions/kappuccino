package br.com.concretesolutions.kappuccino.counters

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.v4.view.ViewPager
import android.view.View
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class ViewPagerItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val viewPager = view as ViewPager
        val adapter = viewPager.adapter
        assertThat(adapter.count, `is`(expectedCount))
    }
}
