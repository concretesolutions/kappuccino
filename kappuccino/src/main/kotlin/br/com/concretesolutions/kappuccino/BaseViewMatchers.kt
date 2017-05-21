package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import br.com.concretesolutions.kappuccino.matchers.DrawableMatcher
import br.com.concretesolutions.kappuccino.matchers.TextColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class BaseViewMatchers : KappuccinoMethods {

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

    override fun parent(@IdRes parentId: Int, func: BaseViewMatchers.() -> Unit) {
        val parentList = BaseViewMatchers().apply { func() }.matchList()
        var parentViewMatcher: Matcher<View>
        for (matcher in parentList) {
            if (parentId != -1)
                parentViewMatcher = Matchers.allOf(isDescendantOfA(withId(parentId)), matcher)
            else
                parentViewMatcher = isDescendantOfA(Matchers.allOf(matcher))
            matchList.add(parentViewMatcher)
        }
    }

    override fun descendant(@IdRes descendantId: Int, func: BaseViewMatchers.() -> Unit) {
        val descendantList = BaseViewMatchers().apply { func() }.matchList()
        var descendantViewMatcher: Matcher<View>
        for (matcher in descendantList) {
            if (descendantId != -1)
                descendantViewMatcher = Matchers.allOf(isDescendantOfA(withId(descendantId)), matcher)
            else
                descendantViewMatcher = isDescendantOfA(Matchers.allOf(matcher))
            matchList.add(descendantViewMatcher)
        }
    }

    fun matchList() = matchList
}

