package br.com.concretesolutions.kappuccino.assertions

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import br.com.concretesolutions.kappuccino.withDrawable
import org.hamcrest.Matchers.not

class VisibilityAssertions(val checkVisible: Boolean, val scroll: Boolean, vararg parentId: Int?, descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll = scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(@IdRes viewId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.id(viewId, scroll)
            .check(visibility())
        return this
    }

    fun text(@StringRes textId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.text(textId, scroll)
            .check(visibility())
        return this
    }

    fun text(text: String, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.text(text, scroll)
            .check(visibility())
        return this
    }

    fun idAndText(@IdRes viewId: Int, @StringRes textId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.idAndText(viewId, textId, scroll)
            .check(visibility())
        return this
    }

    fun idAndText(@IdRes viewId: Int, text: String, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.idAndText(viewId, text, scroll)
            .check(visibility())
        return this
    }

    fun contentDescription(@StringRes contentDescriptionId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.contentDescription(contentDescriptionId, scroll)
            .check(visibility())
        return this
    }

    fun contentDescription(contentDescriptionText: String, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.contentDescription(contentDescriptionText, scroll)
            .check(visibility())
        return this
    }

    fun image(@IdRes imageId: Int, @DrawableRes drawableId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.image(imageId, scroll)
            .check(matches(withDrawable(drawableId)))
        return this
    }

    fun textColor(@IdRes viewId: Int, @ColorRes colorId: Int, scroll: Boolean = this.scroll): VisibilityAssertions {
        viewMatcher.textColor(viewId, colorId, scroll).check(visibility())
        return this
    }

    private fun visibility(): ViewAssertion {
        if (checkVisible) return matches(isDisplayed())
        return matches(not(isDisplayed()))
    }
}
