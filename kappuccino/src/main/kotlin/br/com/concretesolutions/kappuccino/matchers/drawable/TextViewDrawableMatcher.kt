package br.com.concretesolutions.kappuccino.matchers.drawable

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import br.com.concretesolutions.kappuccino.utils.getDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.TextView
import br.com.concretesolutions.kappuccino.utils.compareDrawables
import org.hamcrest.Description
import org.hamcrest.Matcher

class TextViewDrawableMatcher {
    private val START_DRAWABLE = "Start"
    private val END_DRAWABLE = "End"
    private val TOP_DRAWABLE = "Top"
    private val BOTTOM_DRAWABLE = "BOTTOM"

    fun withCompoundDrawable(@DrawableRes startResId: Int? = null,
                             @DrawableRes endResId: Int? = null,
                             @DrawableRes topResId: Int? = null,
                             @DrawableRes bottomResId: Int? = null): Matcher<View> {
        val context = InstrumentationRegistry.getTargetContext()

        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            internal var mismatchDrawablePosition: String? = null
            override fun describeTo(description: Description) {
                description.appendText("Expected but didn't match: $mismatchDrawablePosition compound drawable")
            }

            fun checkResIdAgainstDrawable(@DrawableRes resId: Int?,
                                          drawable: Drawable?,
                                          drawablePositionName: String ): Boolean {
                if (resId == null && drawable == null) {
                    return true
                } else if (resId == null || drawable == null ) {
                    mismatchDrawablePosition = drawablePositionName
                    return false
                }  else if (compareDrawables(getDrawable(context, resId), drawable )) {
                    return true
                }
                mismatchDrawablePosition = drawablePositionName
                return false
            }

            override fun matchesSafely(actual: TextView): Boolean =
                    checkResIdAgainstDrawable(startResId, actual.compoundDrawables[0], START_DRAWABLE) &&
                            checkResIdAgainstDrawable(endResId, actual.compoundDrawables[1], END_DRAWABLE) &&
                            checkResIdAgainstDrawable(topResId, actual.compoundDrawables[2], TOP_DRAWABLE) &&
                            checkResIdAgainstDrawable(bottomResId, actual.compoundDrawables[3], BOTTOM_DRAWABLE)
        }
    }
}