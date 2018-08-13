package br.com.concretesolutions.kappuccino.custom.recyclerView

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
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
import br.com.concretesolutions.kappuccino.extensions.longClickItem
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
        for (position in positions)
            Interactions(recyclerViewId, position).apply { func() }
        return this
    }

    class Interactions(val recyclerViewId: Int, val position: Int) {

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

        fun typeText(@IdRes viewId: Int, text: String): Interactions {
            onView(withId(recyclerViewId)).typeTextOnChildView(position, viewId, text)
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

        fun displayed(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            BaseViewInteractions(false, itemMatchList(func)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            return this
        }

        fun notDisplayed(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            BaseViewInteractions(false, itemMatchList(func)).check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
            return this
        }

        fun notExist(func: BaseMatchersImpl.() -> BaseMatchersImpl): Interactions {
            BaseViewInteractions(false, itemMatchList(func)).check(ViewAssertions.doesNotExist())
            return this
        }

        fun scroll(): Interactions {
            onView(withId((recyclerViewId))).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            return this
        }

        private fun ViewInteraction.swipeLeft(position: Int): ViewInteraction {
            return this.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position, ViewActions.swipeLeft()))
        }

        private fun ViewInteraction.swipeRight(position: Int): ViewInteraction {
            return this.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position, ViewActions.swipeRight()))
        }

        private fun itemMatchList(func: BaseMatchersImpl.() -> BaseMatchersImpl): List<Matcher<View>> {
            val matchList = BaseMatchersImpl().apply { func() }.matchList()
            return matchList.map { matchAtPosition(position, recyclerViewId, it) }
        }
    }
}
