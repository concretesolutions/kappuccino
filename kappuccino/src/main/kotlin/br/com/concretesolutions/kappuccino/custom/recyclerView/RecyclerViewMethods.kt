package br.com.concretesolutions.kappuccino.custom.recyclerView

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concretesolutions.kappuccino.BaseMatchersImpl
import br.com.concretesolutions.kappuccino.BaseViewInteractions
import br.com.concretesolutions.kappuccino.counters.CountAssertion
import br.com.concretesolutions.kappuccino.extensions.clickChildView
import br.com.concretesolutions.kappuccino.extensions.clickItem
import br.com.concretesolutions.kappuccino.extensions.displayed
import br.com.concretesolutions.kappuccino.extensions.displayedChildView
import br.com.concretesolutions.kappuccino.extensions.longClickItem
import br.com.concretesolutions.kappuccino.extensions.notDisplayedChildView
import br.com.concretesolutions.kappuccino.extensions.swipeLeft
import br.com.concretesolutions.kappuccino.extensions.swipeRight
import br.com.concretesolutions.kappuccino.extensions.typeTextOnChildView
import br.com.concretesolutions.kappuccino.matchers.RecyclerViewMatcher.matchAtPosition
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

class RecyclerViewMethods(private val recyclerViewId: Int) {

    fun sizeIs(size: Int): RecyclerViewMethods {
        onView(withId((recyclerViewId))).check(CountAssertion(size))
        return this
    }

    fun atPosition(vararg positions: Int, func: Interactions.() -> Interactions): RecyclerViewMethods {
        for (position in positions) {
            Interactions(recyclerViewId, position).apply { func() }
        }
        return this
    }

    class Interactions(private val recyclerViewId: Int, private val position: Int) {

        fun click(): Interactions {
            onView(withId((recyclerViewId))).clickItem(position)
            return this
        }

        fun longClick(): Interactions {
            onView(withId((recyclerViewId))).longClickItem(position)
            return this
        }

        fun clickChildView(@IdRes viewId: Int): Interactions {
            onView(withId((recyclerViewId))).clickChildView(position, viewId)
            return this
        }

        fun typeText(@IdRes editTextId: Int, text: String): Interactions {
            onView(withId(recyclerViewId)).typeTextOnChildView(position, editTextId, text)
            closeSoftKeyboard()
            return this
        }

        fun swipeLeft(): Interactions {
            onView(withId(recyclerViewId)).swipeLeft(position)
            return this
        }

        fun swipeRight(): Interactions {
            onView(withId(recyclerViewId)).swipeRight(position)
            return this
        }

        /**
         * This method is deprecated until [this issue](https://github.com/concretesolutions/kappuccino/issues/99) is solved.
         * I do not recommend the use when you try to match using the Id of the view. But it seems to work fine if
         * you use the Text instead of the Id.
         * Anyways, use carefully or, use one the following methods instead:
         *
         * @use [displayed] to assert that the whole item is displayed.
         * @use [displayedChildView] to assert that a specific child view of that item is displayed.
         *
         * @deprecated use [displayed] or [displayedChildView] instead.
         */
        @Deprecated(
                level = DeprecationLevel.WARNING,
                message = "User displayed() or displayedChildView(childViewId) instead")
        fun displayed(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            BaseViewInteractions(false, itemMatchList(func)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            return this
        }

        fun displayed(): Interactions {
            onView(withId(recyclerViewId)).displayed(position)
            return this
        }

        fun displayedChildView(@IdRes childViewId: Int): Interactions {
            onView(withId(recyclerViewId)).displayedChildView(position, childViewId)
            return this
        }

        /**
         * This method is deprecated until [this issue](https://github.com/concretesolutions/kappuccino/issues/99) is solved.
         * I do not recommend the use when you try to match using the Id of the view. But it seems to work fine if
         * you use the Text instead of the Id.
         * Anyways, use carefully or, use one the following methods instead:
         *
         * @use [notDisplayedChildView]
         *
         * @deprecated use [notDisplayedChildView] to assert over a child view.
         */
        @Deprecated(
                level = DeprecationLevel.WARNING,
                message = "Use RecyclerViewMethods.Interactions.notDisplayedChildView(childViewId) instead",
                replaceWith = ReplaceWith(
                        expression = "RecyclerViewMethods.Interactions.notDisplayedChildView(childViewId)",
                        imports = ["br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewMethods.Interactions"]))
        fun notDisplayed(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            val assertion = ViewAssertions.matches(not(ViewMatchers.isDisplayed()))
            BaseViewInteractions(false, itemMatchList(func)).check(assertion)
            return this
        }

        fun notDisplayedChildView(@IdRes childViewId: Int): Interactions {
            onView(withId(recyclerViewId)).notDisplayedChildView(position, childViewId)
            return this
        }

        /**
         * This method is deprecated until [this issue](https://github.com/concretesolutions/kappuccino/issues/99) is solved.
         * I do not recommend the use when you try to match using the Id of the view. But it seems to work fine if
         * you use the Text instead of the Id.
         * Anyways, use carefully or, use one the following methods instead:
         *
         * @use [notDisplayedChildView]
         *
         * @deprecated use [notDisplayedChildView] to assert over a child view.
         */
        @Deprecated(
                level = DeprecationLevel.WARNING,
                message = "Use RecyclerViewMethods.Interactions.notDisplayedChildView(childViewId) instead",
                replaceWith = ReplaceWith(
                        expression = "RecyclerViewMethods.Interactions.notDisplayedChildView(childViewId)",
                        imports = ["br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewMethods.Interactions"]
                ))
        fun notExist(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            BaseViewInteractions(false, itemMatchList(func)).check(ViewAssertions.doesNotExist())
            return this
        }

        fun scroll(): Interactions {
            onView(withId((recyclerViewId))).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            return this
        }

        @Deprecated(message = "Don't use this method to try to match in a recycler view")
        private fun itemMatchList(func: BaseMatchersImpl.() -> BaseMatchersImpl): List<Matcher<View>> {
            val matchList = BaseMatchersImpl().apply { func() }.matchList()
            return matchList.map { matchAtPosition(position, it) }
        }
    }
}