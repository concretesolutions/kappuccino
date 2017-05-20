package br.com.concretesolutions.kappuccino.custom.recyclerView

import android.support.annotation.IdRes

object RecyclerViewInteractions {
    // Recycler View
    fun recyclerView(@IdRes recyclerViewId: Int, func: RecyclerViewMethods.() -> Unit) = RecyclerViewMethods(recyclerViewId).apply { func() }
}