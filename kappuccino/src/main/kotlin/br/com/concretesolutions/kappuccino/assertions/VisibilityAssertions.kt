package br.com.concretesolutions.kappuccino.assertions

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import org.hamcrest.Matchers

object VisibilityAssertions {

    fun displayed(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(visibility(true))
    }

    fun notDisplayed(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(visibility(false))
    }

    private fun visibility(checkVisible: Boolean): ViewAssertion {
        if (checkVisible) return ViewAssertions.matches(ViewMatchers.isDisplayed())
        return ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed()))
    }
}