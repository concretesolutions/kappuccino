package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import org.hamcrest.Matchers.not

object ClickableAssertions {

    fun clickable(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(clickable(true))
    }

    fun notClickable(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(clickable(false))
    }

    private fun clickable(clickable: Boolean): ViewAssertion {
        if (clickable) return matches(isClickable())
        else return matches(not(isClickable()))
    }
}

