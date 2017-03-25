package br.com.concretesolutions.kappuccino

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.action.ViewActions
import br.com.concretesolutions.kappuccino.actions.DoubleClick
import br.com.concretesolutions.kappuccino.actions.LongClick
import br.com.concretesolutions.kappuccino.actions.SingleClick
import br.com.concretesolutions.kappuccino.utils.doWait
import br.com.concretesolutions.kappuccino.utils.doWait


// Click
fun click(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: SingleClick.() -> Unit) = SingleClick(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun doubleClick(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: DoubleClick.() -> Unit) = DoubleClick(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun longClick(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: LongClick.() -> Unit) = LongClick(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }


// Text
fun typeText(
        id: Int,
        text: String,
        scroll: Boolean = true,
        pressActionButton: Boolean = false,
        closeKeyboard: Boolean = true,
        vararg parentId: Int? = arrayOf<Int>()) {

    displayed(scroll = scroll, parentId = *parentId) {
        id(id)
    }

    doWait()
    withId(id, *parentId).type(text, pressActionButton, closeKeyboard)
}

fun clearText(
        id: Int,
        scroll: Boolean = true,
        vararg parentId: Int? = arrayOf<Int>()) {

    displayed(scroll = scroll, parentId = *parentId) {
        id(id)
    }

    doWait()
    withId(id, *parentId).perform(ViewActions.clearText())
}

// Recycler View
fun withRecycler(recyclerViewId: Int, func: RecyclerViewInteractions.() -> Unit) = RecyclerViewInteractions(recyclerViewId).apply { func() }