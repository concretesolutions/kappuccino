package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.ViewInteraction

class BaseViewMatchers(val scroll: Boolean, vararg val parentId: Int?, val descendantId: Int?, val descendantText: Int?) {

    fun id(@IdRes viewId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withId(viewId, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)
            .scroll(scroll)
    }

    fun text(@StringRes textId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withText(textId, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)
            .scroll(scroll)
    }

    fun text(text: String, scroll: Boolean = this.scroll): ViewInteraction {
        return withText(text, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)
            .scroll(scroll)
    }

    fun idAndText(@IdRes viewId: Int, @StringRes textId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withIdAndText(viewId, textId)
            .scroll(scroll)
    }

    fun idAndText(@IdRes viewId: Int, text: String, scroll: Boolean = this.scroll): ViewInteraction {
        return withIdAndText(viewId, text = text)
            .scroll(scroll)
    }

    fun contentDescription(@StringRes contentDescriptionId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withContentDescription(contentDescriptionId, *parentId)
            .scroll(scroll)
    }

    fun contentDescription(contentDescriptionText: String, scroll: Boolean = this.scroll): ViewInteraction {
        return withContentDescription(contentDescriptionText, *parentId)
            .scroll(scroll)
    }

    fun image(@IdRes imageId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withId(imageId, *parentId)
            .scroll(scroll)
    }

    fun textColor(@IdRes viewId: Int, @ColorRes colorId: Int, scroll: Boolean = this.scroll): ViewInteraction {
        return withTextColor(viewId, colorId)
            .scroll(scroll)
    }

}

