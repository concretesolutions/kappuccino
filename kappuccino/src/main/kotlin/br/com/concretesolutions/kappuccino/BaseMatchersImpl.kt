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

    override fun id(@IdRes viewId: Int): BaseMatchersImpl {
        matchList.add(ViewMatchers.withId(viewId))
        return this
    }

    override fun text(@StringRes textId: Int): BaseMatchersImpl {
        matchList.add(ViewMatchers.withText(textId))
        return this
    }

    override fun text(text: String): BaseMatchersImpl {
        matchList.add(ViewMatchers.withText(text))
        return this
    }

    override fun text(textMatcher: Matcher<String>) : BaseMatchersImpl {
        matchList.add(ViewMatchers.withText(textMatcher))
        return this
    }

    override fun contentDescription(@StringRes contentDescriptionId: Int): BaseMatchersImpl {
        matchList.add(ViewMatchers.withContentDescription(contentDescriptionId))
        return this
    }

    override fun contentDescription(contentDescription: String): BaseMatchersImpl {
        matchList.add(ViewMatchers.withContentDescription(contentDescription))
        return this
    }

    override fun image(@DrawableRes imageId: Int): BaseMatchersImpl {
        matchList.add(DrawableMatcher(imageId))
        return this
    }

    override fun textColor(@ColorRes colorId: Int): BaseMatchersImpl {
        matchList.add(TextColorMatcher().withTextColor(colorId))
        return this
    }

    fun allOf(func: BaseMatchersImpl.() -> BaseMatchersImpl): BaseMatchersImpl {
        matchList.add(Matchers.allOf(BaseMatchersImpl().apply { func() }.matchList()))
        return this
    }

    override fun parent(@IdRes parentId: Int, func: BaseMatchersImpl.() -> BaseMatchersImpl): BaseMatchersImpl {
        val parentList = BaseMatchersImpl().apply { func() }.matchList()
        parentList.mapTo(matchList) { getParentViewMatcher(parentId, it) }
        return this
    }

    override fun descendant(@IdRes descendantId: Int, func: BaseMatchersImpl.() -> BaseMatchersImpl): BaseMatchersImpl {
        val descendantList = BaseMatchersImpl().apply { func() }.matchList()
        descendantList.mapTo(matchList) { getDescendantViewMatcher(descendantId, it) }
        return this
    }

    override fun custom(viewMatcher: Matcher<View>): BaseMatchersImpl {
        matchList.add(viewMatcher)
        return this
    }

    internal fun matchList() = matchList

    private fun getParentViewMatcher(parentId: Int, matcher: Matcher<View>): Matcher<View> {
        if (parentId != -1)
            return Matchers.allOf(isDescendantOfA(withId(parentId)), matcher)
        else
            return isDescendantOfA(Matchers.allOf(matcher))
    }

    private fun getDescendantViewMatcher(descendantId: Int, matcher: Matcher<View>): Matcher<View> {
        if (descendantId != -1)
            return Matchers.allOf(hasDescendant(withId(descendantId)), matcher)
        else
            return hasDescendant(Matchers.allOf(matcher))
    }
}

