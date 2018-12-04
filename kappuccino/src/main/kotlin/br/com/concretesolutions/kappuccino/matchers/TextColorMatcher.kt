package br.com.concretesolutions.kappuccino.matchers

import androidx.annotation.ColorRes
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.Matcher

class TextColorMatcher {

    fun withTextColor(@ColorRes colorId: Int): Matcher<View> {
        val context = InstrumentationRegistry.getContext()
        val expectedColor = ContextCompat.getColor(context, colorId)
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            internal var currentColor = 0
            override fun describeTo(description: Description) {
                description.appendText("expected TextColor: ")
                    .appendValue(Integer.toHexString(expectedColor))
                description.appendText(" current TextColor: ")
                    .appendValue(Integer.toHexString(currentColor))
            }

            override fun matchesSafely(item: TextView): Boolean {
                if (currentColor == 0) currentColor = item.currentTextColor
                return currentColor == expectedColor
            }
        }
    }
}
