package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import org.hamcrest.Matchers


object VisibilityAssertions {

    fun displayed(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(visibility(true))
    }

    fun notDisplayed(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(visibility(false))
    }

    private fun visibility(checkVisible: Boolean): ViewAssertion {
        if (checkVisible) return ViewAssertions.matches(ViewMatchers.isDisplayed())
        return ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed()))
    }
}