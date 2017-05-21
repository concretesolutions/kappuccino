package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.BaseViewMatchers


object ExistanceAssertions {

    // Important: here the scroll must be false, otherwise espresso will try to scroll that a view that does not exist.
    fun notExist(func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(false, matchList).check(doesNotExist())
    }
}
