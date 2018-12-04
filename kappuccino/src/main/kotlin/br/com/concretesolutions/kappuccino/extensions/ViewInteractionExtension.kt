package br.com.concretesolutions.kappuccino.extensions

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewItemInteractions
import org.hamcrest.Matchers

fun ViewInteraction.scroll(scroll: Boolean): ViewInteraction {
    if (scroll) return this.perform(scrollTo())
    return this
}

fun ViewInteraction.type(text: String, pressActionButton: Boolean = false, closeKeyboard: Boolean = true): ViewInteraction {
    if (pressActionButton) return perform(typeText(text), pressImeActionButton())
    if (closeKeyboard) return perform(typeText(text), closeSoftKeyboard())
    return perform(typeText(text))
}

//region RecyclerView specific extensions
internal fun ViewInteraction.clickItem(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions
        .actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
}

internal fun ViewInteraction.longClickItem(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions
        .actionOnItemAtPosition<RecyclerView.ViewHolder>(position, longClick()))
}

internal fun ViewInteraction.clickChildView(position: Int, viewId: Int): ViewInteraction {
    return this.perform(RecyclerViewItemInteractions.actionOnItemViewAtPosition(position, viewId, click()))
}

internal fun ViewInteraction.typeTextOnChildView(position: Int, @IdRes viewId: Int, text: String): ViewInteraction {
    return this.perform(RecyclerViewItemInteractions.actionOnItemViewAtPosition(position, viewId, typeText(text)))
}

internal fun ViewInteraction.notDisplayedChildView(position: Int, @IdRes viewId: Int): ViewInteraction {
    val viewAssertion = ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed()))
    return this.check(RecyclerViewItemInteractions.assertionOnItemViewAtPosition(position, viewId, viewAssertion))
}

internal fun ViewInteraction.displayed(position: Int): ViewInteraction {
    val viewAssertion = ViewAssertions.matches(ViewMatchers.isDisplayed())
    return this.check(RecyclerViewItemInteractions.assertionOnItemViewAtPosition(position, RecyclerViewItemInteractions.rootViewId, viewAssertion))
}

internal fun ViewInteraction.displayedChildView(position: Int, @IdRes viewId: Int): ViewInteraction {
    val viewAssertion = ViewAssertions.matches(ViewMatchers.isDisplayed())
    return this.check(RecyclerViewItemInteractions.assertionOnItemViewAtPosition(position, viewId, viewAssertion))
}

internal fun ViewInteraction.swipeLeft(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position, ViewActions.swipeLeft()))
}

internal fun ViewInteraction.swipeRight(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position, ViewActions.swipeRight()))
}
//endregion