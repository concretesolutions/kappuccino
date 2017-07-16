package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isChecked
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import org.hamcrest.Matchers.not

object CheckedAssertions {

    fun checked(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(checked(true))
    }

    fun notChecked(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(checked(false))
    }

    private fun checked(checked: Boolean): ViewAssertion {
        if (checked) return matches(isChecked())
        else return matches(not(isChecked()))
    }
}

