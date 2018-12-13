package br.com.concretesolutions.kappuccino.custom.textInputLayout

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import org.hamcrest.Description

fun textInputLayout(@IdRes idTextInputLayout: Int, assertion: TextInputLayoutAssertions.() -> TextInputLayoutAssertions): TextInputLayoutAssertions {
    return TextInputLayoutAssertions(idTextInputLayout).apply {
        assertion()
    }
}

class TextInputLayoutAssertions(@IdRes private val idTextInputLayout: Int) {

    fun hasTextError(): TextInputLayoutAssertions {
        onView(withId(idTextInputLayout)).check(matches(matcherHasTextError()))
        return this
    }

    fun withTextError(@StringRes textToCheck: Int): TextInputLayoutAssertions {
        val targetContext = InstrumentationRegistry.getTargetContext()
        val matcherToCheckErrorText = boundedMatcherToCheckErrorText(targetContext.resources.getString(textToCheck))
        onView(withId(idTextInputLayout)).check(matches(matcherToCheckErrorText))
        return this
    }

    fun withTextError(textToCheck: String): TextInputLayoutAssertions {
        val matcherToCheckErrorText = boundedMatcherToCheckErrorText(textToCheck)
        onView(withId(idTextInputLayout)).check(matches(matcherToCheckErrorText))
        return this
    }

    private fun matcherHasTextError(): BoundedMatcher<View, TextInputLayout> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText(" has text error")
            }

            override fun matchesSafely(item: TextInputLayout): Boolean {
                return item.error != null
            }
        }
    }

    private fun boundedMatcherToCheckErrorText(textToCheck: String): BoundedMatcher<View, TextInputLayout> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText(" has text error")
            }

            override fun matchesSafely(item: TextInputLayout): Boolean {
                return item.error != null
            }
        }
    }
}