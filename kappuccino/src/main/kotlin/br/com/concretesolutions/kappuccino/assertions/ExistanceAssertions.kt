package br.com.concretesolutions.kappuccino.assertions

import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions

object ExistanceAssertions {

    // Important: here the scroll must be false, otherwise espresso will try to scroll that a view that does not exist.
    fun notExist(func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(false, matchList).check(doesNotExist())
    }
}