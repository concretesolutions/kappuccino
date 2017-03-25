package br.com.concretesolutions.kappuccino

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import br.com.concretesolutions.kappuccino.matchers.RecyclerViewMatcher
import br.com.concretesolutions.kappuccino.utils.RecyclerViewTestUtils
import org.hamcrest.Matchers.not


/**
 * This class interacts with a view INSIDE a recyclerview item, at a certain position.
 * If you want to just perform a click on a item of a recyclerview, use the click method at BaseActions.kt
* */
class RecyclerViewInteractions(val recyclerViewId: Int) {

    fun atPosition(position: Int, func: Interactions.() -> Unit): RecyclerViewInteractions {
        return atPosition(listOf(position), func)

    }

    fun atPosition(positionList: List<Int>, func: Interactions.() -> Unit): RecyclerViewInteractions {
        for(position in positionList)
            Interactions(recyclerViewId, position).apply { func() }
        return this
    }

    class Interactions(val recyclerViewId: Int, val position: Int) {

        fun click(viewId: Int): Interactions {
            withId(recyclerViewId)
                .perform(
                    RecyclerViewTestUtils.actionOnItemViewAtPosition(position, viewId, click()))
            return this
        }

        fun displayed(viewId: Int): Interactions {
            Espresso.onView(RecyclerViewMatcher(recyclerViewId)
                .atPositionOnView(position, viewId)).check(ViewAssertions.matches(isDisplayed()))
            return this
        }

        fun notDisplayed(viewId: Int): Interactions {
            Espresso.onView(RecyclerViewMatcher(recyclerViewId)
                .atPositionOnView(position, viewId)).check(ViewAssertions.matches(not(isDisplayed())))
            return this
        }
    }

}

