package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.view.View
import br.com.concretesolutions.kappuccino.matchers.DrawableMatcher
import br.com.concretesolutions.kappuccino.matchers.TextColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class BaseViewMatchers : BaseMatcherMethods {

    private val matchList = mutableListOf<Matcher<View>>()

    override fun id(@IdRes viewId: Int) {
        matchList.add(ViewMatchers.withId(viewId))
    }

    override fun text(@StringRes textId: Int) {
        matchList.add(ViewMatchers.withText(textId))
    }

    override fun text(text: String) {
        matchList.add(ViewMatchers.withText(text))
    }

    override fun contentDescription(@StringRes contentDescriptionId: Int) {
        matchList.add(ViewMatchers.withContentDescription(contentDescriptionId))
    }

    override fun contentDescription(contentDescription: String) {
        matchList.add(ViewMatchers.withContentDescription(contentDescription))
    }

    override fun image(@DrawableRes imageId: Int) {
        matchList.add(DrawableMatcher(imageId))
    }

    override fun textColor(@ColorRes colorId: Int) {
        matchList.add(TextColorMatcher().withTextColor(colorId))
    }

    fun allOf(func: BaseViewMatchers.() -> Unit) {
        matchList.add(Matchers.allOf(BaseViewMatchers().apply { func() }.matchList()))
    }

    override fun parent(func: BaseViewMatchers.() -> Unit) {
        matchList.add(isDescendantOfA(Matchers.allOf(BaseViewMatchers().apply { func() }.matchList())))
    }

    override fun descendant(func: BaseViewMatchers.() -> Unit) {
        matchList.add(hasDescendant(Matchers.allOf(BaseViewMatchers().apply { func() }.matchList())))
    }

    fun matchList() = matchList
}

