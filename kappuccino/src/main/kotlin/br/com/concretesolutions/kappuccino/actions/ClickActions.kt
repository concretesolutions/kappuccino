package br.com.concretesolutions.kappuccino.actions

import android.support.test.espresso.action.ViewActions
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.BaseMatchersImpl


object ClickActions {

    fun click(scroll: Boolean = false, func: BaseMatchersImpl.() -> Unit) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.click())
    }

    fun doubleClick(scroll: Boolean = false, func: BaseMatchersImpl.() -> Unit) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.doubleClick())
    }

    fun longClick(scroll: Boolean = false, func: BaseMatchersImpl.() -> Unit) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.longClick())
    }

}
