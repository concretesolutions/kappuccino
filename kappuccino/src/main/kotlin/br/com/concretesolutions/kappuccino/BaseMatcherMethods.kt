package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.view.View
import org.hamcrest.Matcher

interface BaseMatcherMethods {
    fun id(@IdRes viewId: Int): Any
    fun text(@StringRes textId: Int): Any
    fun text(text: String): Any
    /**
     * Matches a view if it has text that matches the Matcher
     */
    fun text(textMatcher: Matcher<String>) : Any
    fun contentDescription(@StringRes contentDescriptionId: Int): Any
    fun contentDescription(contentDescription: String): Any
    fun image(@DrawableRes imageId: Int): Any
    fun textColor(@ColorRes colorId: Int): Any
    fun parent(@IdRes parentId: Int = -1, func: BaseMatchersImpl.() -> BaseMatchersImpl): Any
    fun descendant(@IdRes descendantId: Int = -1, func: BaseMatchersImpl.() -> BaseMatchersImpl): Any
    fun custom(viewMatcher: Matcher<View>): Any
}