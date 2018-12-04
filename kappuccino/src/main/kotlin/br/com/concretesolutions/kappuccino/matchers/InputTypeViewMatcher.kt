package br.com.concretesolutions.kappuccino.matchers

import androidx.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.EditText
import org.hamcrest.Description
import org.hamcrest.Matcher

object InputTypeViewMatcher {

    fun hasInputType(inputType: Int): Matcher<View> {
        return object : BoundedMatcher<View, EditText>(EditText::class.java) {
            internal var targetViewInputType = 0

            override fun describeTo(description: Description) {
                description.appendText("hasInputType: ").appendValue(Integer.toHexString(inputType))
                description.appendText(" actualInputType: ")
                        .appendValue(Integer.toHexString(targetViewInputType))
                description.appendText(" mask: ")
                        .appendValue(Integer.toHexString(inputType and targetViewInputType))
            }

            override fun matchesSafely(item: EditText): Boolean {
                if (targetViewInputType == 0) {
                    targetViewInputType = item.inputType
                }
                return targetViewInputType and inputType == inputType
            }
        }
    }
}
