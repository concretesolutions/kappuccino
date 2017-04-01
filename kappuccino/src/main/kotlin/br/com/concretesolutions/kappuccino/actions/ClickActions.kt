package br.com.concretesolutions.kappuccino.actions

import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.doubleClick
import android.support.test.espresso.action.ViewActions.longClick
import br.com.concretesolutions.kappuccino.BaseViewMatchers

open class BaseClickAction(protected val clickType: Int, protected val scroll: Boolean, vararg parentId: Int?, descendantId: Int?, descendantText: Int?) {

    private val SINGLE_CLICK = 1
    private val DOUBLE_CLICK = 2
    private val LONG_CLICK = 3

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll, clickType: Int = this.clickType): BaseClickAction {
        viewMatcher.id(viewId, scroll).perform(getClickType(clickType))
        return this
    }

    fun text(textId: Int, scroll: Boolean = this.scroll, clickType: Int = this.clickType): BaseClickAction {
        viewMatcher.text(textId, scroll).perform(getClickType(clickType))
        return this
    }

    fun text(text: String, scroll: Boolean = this.scroll, clickType: Int = this.clickType): BaseClickAction {
        viewMatcher.text(text, scroll).perform(getClickType(clickType))
        return this
    }

    fun contentDescription(contentDescriptionId: Int, scroll: Boolean = this.scroll, clickType: Int = this.clickType): BaseClickAction {
        viewMatcher.contentDescription(contentDescriptionId, scroll).perform(getClickType(clickType))
        return this
    }

    fun contentDescription(contentDescriptionText: String, scroll: Boolean = this.scroll, clickType: Int = this.clickType): BaseClickAction {
        viewMatcher.contentDescription(contentDescriptionText, scroll).perform(getClickType(clickType))
        return this
    }

    private fun getClickType(type: Int): ViewAction {
        when (type) {
            SINGLE_CLICK -> return click()
            DOUBLE_CLICK -> return doubleClick()
            LONG_CLICK -> return longClick()
            else -> return click()
        }
    }
}

class DoubleClick(scroll: Boolean, vararg parentId: Int?, descendantId: Int?, descendantText: Int?) : BaseClickAction(2, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

class LongClick(scroll: Boolean, vararg parentId: Int?, descendantId: Int?, descendantText: Int?) : BaseClickAction(3, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

class SingleClick(scroll: Boolean, vararg parentId: Int?, descendantId: Int?, descendantText: Int?) : BaseClickAction(1, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)


