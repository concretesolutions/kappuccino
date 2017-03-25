package br.com.concretesolutions.kappuccino

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import br.com.concretesolutions.kappuccino.assertions.CheckedAssertions
import br.com.concretesolutions.kappuccino.assertions.ClickableAssertions
import br.com.concretesolutions.kappuccino.assertions.EnableAssertions
import br.com.concretesolutions.kappuccino.assertions.ListSizeAssertions
import br.com.concretesolutions.kappuccino.assertions.NotExistAssertions
import br.com.concretesolutions.kappuccino.assertions.SelectedAssertions
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions

fun displayed(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: VisibilityAssertions.() -> Unit) = VisibilityAssertions(true, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun notDisplayed(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: VisibilityAssertions.() -> Unit) = VisibilityAssertions(false, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun checked(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: CheckedAssertions.() -> Unit) = CheckedAssertions(true, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun notChecked(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: CheckedAssertions.() -> Unit) = CheckedAssertions(false, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun enabled(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: EnableAssertions.() -> Unit) = EnableAssertions(true, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun notEnabled(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: EnableAssertions.() -> Unit) = EnableAssertions(false, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun selected(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: SelectedAssertions.() -> Unit) = SelectedAssertions(true, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun notSelected(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: SelectedAssertions.() -> Unit) = SelectedAssertions(false, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun clickable(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: ClickableAssertions.() -> Unit) = ClickableAssertions(true, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }
fun notClickable(scroll: Boolean = true, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: ClickableAssertions.() -> Unit) = ClickableAssertions(false, scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

// Important: here the scroll must be false, otherwise espresso will try to scroll that a view that does not exist.
fun notExist(scroll: Boolean = false, vararg @IdRes parentId: Int? = arrayOf<Int>(), @IdRes descendantId: Int? = null, @StringRes descendantText: Int? = null, func: NotExistAssertions.() -> Unit) = NotExistAssertions(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText).apply { func() }

fun checkSize(func: ListSizeAssertions.() -> Unit) = ListSizeAssertions().apply { func() }