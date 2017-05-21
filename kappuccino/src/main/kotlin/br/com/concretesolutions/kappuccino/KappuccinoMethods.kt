package br.com.concretesolutions.kappuccino

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes

interface KappuccinoMethods {
    fun id(@IdRes viewId: Int): Any
    fun text(@StringRes textId: Int): Any
    fun text(text: String): Any
    fun contentDescription(@StringRes contentDescriptionId: Int): Any
    fun contentDescription(contentDescription: String): Any
    fun image(@DrawableRes imageId: Int): Any
    fun textColor(@ColorRes colorId: Int): Any
    fun parent(@IdRes parentId: Int = -1, func: BaseViewMatchers.() -> Unit): Any
    fun descendant(@IdRes descendantId: Int = -1, func: BaseViewMatchers.() -> Unit): Any
}