package br.com.concretesolutions.kappuccino.matchers

import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object RecyclerViewMatcher {

    fun matchAtPosition(position: Int, recyclerViewId: Int, itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : TypeSafeMatcher<View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position: $position ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is RecyclerView) {
                    return false
                }
                val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId) as RecyclerView
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position) ?: // has no item on such position
                        return false
                viewHolder.itemView as? ViewGroup ?: return itemMatcher.matches(viewHolder.itemView)
                return ViewMatchers.hasDescendant(itemMatcher).matches(viewHolder.itemView)
            }
        }
    }
}