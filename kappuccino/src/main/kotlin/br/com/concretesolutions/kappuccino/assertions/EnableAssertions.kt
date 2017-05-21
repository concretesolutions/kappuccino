package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import org.hamcrest.Matchers.not

object EnableAssertions {

    fun enabled(scroll: Boolean = false, func: BaseMatchersImpl.() -> Unit) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(enabled(true))
    }

    fun notEnabled(scroll: Boolean = false, func: BaseMatchersImpl.() -> Unit) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(enabled(false))
    }

    private fun enabled(enabled: Boolean): ViewAssertion {
        if (enabled) return matches(isEnabled())
        else return matches(not(isEnabled()))
    }
}

