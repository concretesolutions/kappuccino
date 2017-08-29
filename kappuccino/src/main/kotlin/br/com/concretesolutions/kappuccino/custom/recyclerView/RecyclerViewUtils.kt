package br.com.concretesolutions.kappuccino.custom.recyclerView

import android.support.annotation.IdRes
import android.support.test.espresso.PerformException.Builder
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object RecyclerViewUtils {

    fun actionOnItemViewAtPosition(position: Int, @IdRes viewId: Int, viewAction: ViewAction): ViewAction {
        return ActionOnItemViewAtPositionViewAction(
            position, viewId, viewAction)
    }

    private class ActionOnItemViewAtPositionViewAction (
            private val position: Int,
            @param:IdRes private val viewId: Int,
            private val viewAction: ViewAction) : ViewAction {

        override fun getConstraints(): Matcher<View> = createConstraints()

        override fun getDescription(): String {
            return "actionOnItemAtPosition performing ViewAction: $this.viewAction.description on item at position: $this.position"
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            ScrollToPositionViewAction(this.position).perform(uiController, view)
            uiController.loopMainThreadUntilIdle()

            val targetView = recyclerView.getChildAt(this.position).findViewById<View>(this.viewId)

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

    private class ScrollToPositionViewAction (
        private val position: Int) : ViewAction {

        override fun getConstraints(): Matcher<View> = createConstraints()

        override fun getDescription(): String {
            return "scroll RecyclerView to position: " + this.position
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(this.position)
        }
    }

    private fun createConstraints() = Matchers.allOf(
            ViewMatchers.isAssignableFrom(RecyclerView::class.java), ViewMatchers.isDisplayed())
}
