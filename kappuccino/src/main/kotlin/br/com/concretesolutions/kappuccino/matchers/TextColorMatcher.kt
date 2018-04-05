package br.com.concretesolutions.kappuccino.matchers

import android.support.annotation.ColorRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher

class TextColorMatcher {

    fun withTextColor(@ColorRes colorId: Int): Matcher<View> {
        val context = InstrumentationRegistry.getTargetContext()
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
