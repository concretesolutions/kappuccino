package br.com.concretesolutions.kappuccino.custom.recyclerView

import android.support.annotation.IdRes
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException.Builder
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers

internal object RecyclerViewItemInteractions {

    internal const val rootViewId = -1

    internal fun actionOnItemViewAtPosition(position: Int, @IdRes viewId: Int, viewAction: ViewAction): ViewAction {
        return ActionOnItemViewAtPositionViewAction(position, viewId, viewAction)
    }

    internal fun assertionOnItemViewAtPosition(position: Int, @IdRes viewId: Int = rootViewId, viewAssertion: ViewAssertion): ViewAssertion {
        return AssertionOnItemViewAtPosition(position, viewId, viewAssertion)
    }

    private class ActionOnItemViewAtPositionViewAction (
            private val position: Int,
            @IdRes private val viewId: Int,
            private val viewAction: ViewAction) : ViewAction {

        override fun getConstraints(): Matcher<View> = createConstraints()

        override fun getDescription(): String {
            return "actionOnItemAtPosition performing ViewAction: ${viewAction.description} on item at position: $position"
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView

            ScrollToPositionViewAction(position).perform(uiController, view)
            uiController.loopMainThreadUntilIdle()

            val targetView = recyclerView.getChildAt(position).apply {
                if (viewId == rootViewId) {
                    rootView
                } else {
                    findViewById<View>(viewId)
                }
            }

            if (targetView == null) {
                throw Builder()
                        .withActionDescription(this.toString())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(IllegalStateException("No view with id $viewId found at position: $position"))
                        .build()
            } else {
                viewAction.perform(uiController, targetView)
            }
        }
    }

    private class AssertionOnItemViewAtPosition(private val position: Int,
                                                @IdRes private val viewId: Int,
                                                private val viewAssertion: ViewAssertion) : ViewAssertion {

        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            val recyclerView = view as RecyclerView

            actionOnItemViewAtPosition(position, viewId, ViewActions.scrollTo())

            val childAt = recyclerView.getChildAt(position)
            val targetView = childAt.findViewById<View>(viewId)
                    ?: throw Builder().withCause(noViewFoundException).build()

            viewAssertion.check(targetView, noViewFoundException)
        }
    }

    private class ScrollToPositionViewAction(private val position: Int) : ViewAction {

        override fun getConstraints(): Matcher<View> = createConstraints()

        override fun getDescription(): String {
            return "scroll RecyclerView to position: $position"
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(position)
        }
    }

    private fun createConstraints() = Matchers.allOf(
            ViewMatchers.isAssignableFrom(RecyclerView::class.java), ViewMatchers.isDisplayed())
}