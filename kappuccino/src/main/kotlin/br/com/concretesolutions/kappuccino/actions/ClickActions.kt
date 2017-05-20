package br.com.concretesolutions.kappuccino.actions

import android.support.test.espresso.action.ViewActions
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.BaseViewMatchers


object ClickActions {

    fun click(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.click())
    }

    fun doubleClick(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.doubleClick())
    }

    fun longClick(scroll: Boolean = false, func: BaseViewMatchers.() -> Unit) {
        val matchList = BaseViewMatchers().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.longClick())
    }

}
