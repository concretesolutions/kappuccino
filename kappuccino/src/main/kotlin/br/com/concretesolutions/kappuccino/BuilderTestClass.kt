package br.com.concretesolutions.kappuccino

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers


fun newDisplayed(scroll: Boolean = true, func: MatchersUtils.() -> MatchersUtils) =
    MatchersUtils(scroll).apply {
        func().match(NewVisibilityAssertions.visibility(true))
    }

object NewVisibilityAssertions {
    fun visibility(checkVisible: Boolean): ViewAssertion {
        if (checkVisible) return ViewAssertions.matches(ViewMatchers.isDisplayed())
        return ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed()))
    }
}

open class MatchersUtils(val scroll: Boolean) {

    private val matchers: MutableList<Matcher<View>> = mutableListOf()

    fun id(@IdRes viewId: Int): MatchersUtils {
        matchers.add(ViewMatchers.withId(viewId))
        return this
    }

    fun text(@StringRes textId: Int): MatchersUtils {
        matchers.add(ViewMatchers.withText(textId))
        return this
    }

    fun match(viewAssertion: ViewAssertion) {
        Espresso.onView(Matchers.allOf(matchers)).scroll(scroll).check(viewAssertion)
    }
}
