package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.BaseViewMatchers


object ExistanceAssertions {

    // Important: here the scroll must be false, otherwise espresso will try to scroll that a view that does not exist.
    fun notExist(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).check(doesNotExist())
    }
}
