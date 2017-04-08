package br.com.concretesolutions.kappuccino

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.v7.widget.RecyclerView.ViewHolder
import br.com.concretesolutions.kappuccino.matchers.RecyclerViewMatcher
import br.com.concretesolutions.kappuccino.utils.RecyclerViewTestUtils
import org.hamcrest.Matchers.not

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

        fun clickItem(): Interactions {
            withId(recyclerViewId)
                .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, click()))
            return this
        }

        fun longClickItem(): Interactions {
            withId(recyclerViewId)
                .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, longClick()))
            return this
        }

        fun clickChildView(viewId: Int): Interactions {
            withId(recyclerViewId)
                .perform(
                    RecyclerViewTestUtils.actionOnItemViewAtPosition(position, viewId, click()))
            return this
        }

        //TODO REFATORAR PARA USAR O METODO DISPLAYED QUE JA EXISTE
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

        fun scroll(): Interactions {
            withId(recyclerViewId).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(position))
            return this
        }
    }

}

