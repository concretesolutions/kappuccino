package br.com.concretesolutions.kappuccino.custom.viewPager

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import br.com.concretesolutions.kappuccino.counters.CountAssertion

object ViewPagerInteractions {

    fun viewPager(@IdRes viewPagerId: Int, func: ViewPagerAssertions.() -> Unit) {
        ViewPagerAssertions(viewPagerId).apply { func() }
    }

    class ViewPagerAssertions(private val viewPagerId: Int) {
        fun sizeIs(size: Int) {
            onView(withId(viewPagerId)).check(CountAssertion(size))
        }
    }
}
