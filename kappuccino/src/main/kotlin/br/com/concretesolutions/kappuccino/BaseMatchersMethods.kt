package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.view.View
import br.com.concretesolutions.kappuccino.matchers.DrawableMatcher
import br.com.concretesolutions.kappuccino.matchers.TextColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers

fun withId(viewId: Int, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int? = null, descendantText: Int? = null): ViewInteraction {
    return getViewInteraction(getMatchers(viewId, parentId = *parentId, descendantId = descendantId, descendantText = descendantText))
}

fun withText(textId: Int, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int? = null, descendantText: Int? = null): ViewInteraction {
    return getViewInteraction(getMatchers(textId = textId, parentId = *parentId, descendantId = descendantId, descendantText = descendantText))
}

fun withText(text: String, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int? = null, descendantText: Int? = null): ViewInteraction {
    return getViewInteraction(getMatchers(text = text, parentId = *parentId, descendantId = descendantId, descendantText = descendantText))
}

fun withContentDescription(contentDescriptionId: Int, vararg parentId: Int? = arrayOf<Int>()): ViewInteraction {
    return getViewInteraction(
        getMatchers(contentDescriptionId = contentDescriptionId, parentId = *parentId))
}

fun withContentDescription(contentDescriptionText: String, vararg parentId: Int? = arrayOf<Int>()): ViewInteraction {
    return getViewInteraction(
        getMatchers(contentDescriptionText = contentDescriptionText, parentId = *parentId))
}

fun withDrawable(drawableId: Int): DrawableMatcher {
    return DrawableMatcher(drawableId)
}

fun withTextColor(@IdRes viewId: Int? = null, @ColorRes colorId: Int): ViewInteraction {
    return getViewInteraction(getMatchers(viewId, colorId = colorId))
}

fun withIdAndText(@IdRes viewId: Int? = null, @StringRes stringRes: Int? = null, text: String? = null): ViewInteraction {
    return getViewInteraction(getMatchers(viewId, textId = stringRes, text = text))
}

private fun getViewInteraction(matcherList: List<Matcher<View>>): ViewInteraction {
    return Espresso.onView(Matchers.allOf(matcherList))
}

private fun getMatchers(
    viewId: Int? = null,
    vararg parentId: Int? = arrayOf<Int>(),
    textId: Int? = null,
    text: String? = null,
    contentDescriptionId: Int? = null,
    contentDescriptionText: String? = null,
    descendantId: Int? = null,
    descendantText: Int? = null,
    colorId: Int? = null): List<Matcher<View>> {

    val matcherList: MutableList<Matcher<View>> = mutableListOf()

    if (viewId != null)
        matcherList.add(ViewMatchers.withId(viewId))

    if (parentId.isNotEmpty())
        parentId
            .filterNotNull()
            .mapTo(matcherList) { ViewMatchers.isDescendantOfA(ViewMatchers.withId(it)) }

    if (textId != null)
        matcherList.add(ViewMatchers.withText(textId))

    if (text != null)
        matcherList.add(ViewMatchers.withText(text))

    if (contentDescriptionId != null)
        matcherList.add(ViewMatchers.withContentDescription(contentDescriptionId))

    if (contentDescriptionText != null)
        matcherList.add(ViewMatchers.withContentDescription(contentDescriptionText))

    if (descendantId != null)
        matcherList.add(hasDescendant(ViewMatchers.withId(descendantId)))

    if (descendantText != null)
        matcherList.add(hasDescendant(ViewMatchers.withText(descendantText)))

    if (colorId != null)
        TextColorMatcher().withTextColor(colorId)

    return matcherList
}