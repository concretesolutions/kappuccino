package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import br.com.concretesolutions.kappuccino.matchers.DrawableMatcher
import br.com.concretesolutions.kappuccino.matchers.TextColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class BaseMatchersImpl : BaseMatcherMethods {

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

    fun allOf(func: BaseMatchersImpl.() -> Unit) {
        matchList.add(Matchers.allOf(BaseMatchersImpl().apply { func() }.matchList()))
    }

    override fun parent(@IdRes parentId: Int, func: BaseMatchersImpl.() -> Unit) {
        val parentList = BaseMatchersImpl().apply { func() }.matchList()
        var parentViewMatcher: Matcher<View>
        for (matcher in parentList) {
            if (parentId != -1)
                parentViewMatcher = Matchers.allOf(isDescendantOfA(withId(parentId)), matcher)
            else
                parentViewMatcher = isDescendantOfA(Matchers.allOf(matcher))
            matchList.add(parentViewMatcher)
        }
    }

    override fun descendant(@IdRes descendantId: Int, func: BaseMatchersImpl.() -> Unit) {
        val descendantList = BaseMatchersImpl().apply { func() }.matchList()
        var descendantViewMatcher: Matcher<View>
        for (matcher in descendantList) {
            if (descendantId != -1)
                descendantViewMatcher = Matchers.allOf(hasDescendant(withId(descendantId)), matcher)
            else
                descendantViewMatcher = hasDescendant(Matchers.allOf(matcher))
            matchList.add(descendantViewMatcher)
        }
    }

    override fun custom(viewMatcher: Matcher<View>) {
        matchList.add(viewMatcher)
    }

    internal fun matchList() = matchList
}

