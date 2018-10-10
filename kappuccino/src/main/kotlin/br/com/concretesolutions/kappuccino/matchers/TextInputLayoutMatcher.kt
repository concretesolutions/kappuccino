package br.com.concretesolutions.kappuccino.matchers

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

fun withTextError(textToCheck: String): BoundedMatcher<View, TextInputLayout> {
    return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
        override fun describeTo(description: Description) {
            description
                    .appendText(" expected text was: ")
                    .appendText(textToCheck)
        }

        override fun matchesSafely(item: TextInputLayout): Boolean {
            return item.error == textToCheck
        }
    }
}

fun hasTextError(): BoundedMatcher<View, TextInputLayout> {
    return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
        override fun describeTo(description: Description) {
            description.appendText(" has text error")
        }

        override fun matchesSafely(item: TextInputLayout): Boolean {
            return item.error != null
        }
    }
}