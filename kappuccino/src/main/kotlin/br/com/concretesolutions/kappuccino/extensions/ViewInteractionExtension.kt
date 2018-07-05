package br.com.concretesolutions.kappuccino.extensions

import android.support.annotation.IdRes
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.v7.widget.RecyclerView.ViewHolder
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewUtils

fun ViewInteraction.scroll(scroll: Boolean): ViewInteraction {
    if (scroll) return this.perform(scrollTo())
    return this
}

fun ViewInteraction.type(text: String, pressActionButton: Boolean = false, closeKeyboard: Boolean = true): ViewInteraction {
    if (pressActionButton) return perform(typeText(text), pressImeActionButton())
    if (closeKeyboard) return perform(typeText(text), closeSoftKeyboard())
    return perform(typeText(text))
}

fun ViewInteraction.clickItem(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions
        .actionOnItemAtPosition<ViewHolder>(position, click()))
}

fun ViewInteraction.longClickItem(position: Int): ViewInteraction {
    return this.perform(RecyclerViewActions
        .actionOnItemAtPosition<ViewHolder>(position, longClick()))
}

fun ViewInteraction.clickChildView(position: Int, viewId: Int): ViewInteraction {
    return this.perform(RecyclerViewUtils.actionOnItemViewAtPosition(position, viewId, click()))
}

fun ViewInteraction.typeTextOnChildView(position: Int, @IdRes viewId: Int, text: String): ViewInteraction {
    return this.perform(RecyclerViewUtils.actionOnItemViewAtPosition(position, viewId, typeText(text)))
}
