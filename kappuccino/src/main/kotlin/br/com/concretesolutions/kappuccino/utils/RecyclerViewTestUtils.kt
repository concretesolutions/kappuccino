package br.com.concretesolutions.kappuccino.utils

import android.support.annotation.IdRes
import android.support.test.espresso.PerformException.Builder
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concretesolutions.kappuccino.counters.RecyclerViewItemCountAssertion
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object RecyclerViewTestUtils {

    fun actionOnItemViewAtPosition(position: Int, @IdRes viewId: Int, viewAction: ViewAction): ViewAction {
        return RecyclerViewTestUtils.ActionOnItemViewAtPositionViewAction(
            position, viewId, viewAction)
    }

    private class ActionOnItemViewAtPositionViewAction (
        private val position: Int,
        @param:IdRes private val viewId: Int,
        private val viewAction: ViewAction) : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(
                *arrayOf<Matcher<View>>(ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()))
        }

        override fun getDescription(): String {
            return "actionOnItemAtPosition performing ViewAction: $this.viewAction.description on item at position: $this.position"
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            RecyclerViewTestUtils.ScrollToPositionViewAction(this.position).perform(uiController, view)
            uiController.loopMainThreadUntilIdle()

            val targetView = recyclerView.getChildAt(this.position).findViewById(this.viewId)

            if (targetView == null) {
                throw Builder().withActionDescription(this.toString())
                    .withViewDescription(

                        HumanReadables.describe(view))
                    .withCause(IllegalStateException(
                        "No view with id "
                            + this.viewId
                            + " found at position: "
                            + this.position))
                    .build()
            } else {
                this.viewAction.perform(uiController, targetView)
            }
        }
    }

    fun recyclerViewHasSize(size: Int): ViewAssertion {
        return RecyclerViewItemCountAssertion(size)
    }

    private class ScrollToPositionViewAction (
        private val position: Int) : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(
                *arrayOf<Matcher<View>>(ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()))
        }

        override fun getDescription(): String {
            return "scroll RecyclerView to position: " + this.position
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(this.position)
        }
    }
}
