package br.com.concretesolutions.kappuccino.actions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.custom.compoundDrawable.ClickDrawableAction
import br.com.concretesolutions.kappuccino.custom.compoundDrawable.ClickDrawableAction.Companion.Location
import br.com.concretesolutions.kappuccino.extensions.scroll
import org.hamcrest.Matchers

object ClickActions {

    fun click(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.click())
    }

    fun doubleClick(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.doubleClick())
    }

    fun longClick(scroll: Boolean = false, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        BaseViewInteractions(scroll, matchList).action(ViewActions.longClick())
    }

    fun clickDrawable(scroll: Boolean = false, @Location location: Int, func: BaseMatchersImpl.() -> BaseMatchersImpl) {
        val matchList = BaseMatchersImpl().apply { func() }.matchList()
        val matcher = Matchers.allOf(matchList)
        onView(matcher).scroll(scroll).perform(ClickDrawableAction(location))
    }
}
