package br.com.concretesolutions.kappuccino.matchers.drawable

import android.support.annotation.DrawableRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.TextView
import br.com.concretesolutions.kappuccino.utils.checkResIdAgainstDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher

sealed class DrawablePosition {
    data class Start(@DrawableRes val resId: Int) : DrawablePosition()
    data class End(@DrawableRes val resId: Int) : DrawablePosition()
    data class Top(@DrawableRes val resId: Int) : DrawablePosition()
    data class Bottom(@DrawableRes val resId: Int) : DrawablePosition()

    override fun toString(): String {
        return this::class.java.name
    }
}

class TextViewDrawableMatcher {
    fun withCompoundDrawable(drawablePosition: DrawablePosition): Matcher<View> {
        val context = InstrumentationRegistry.getTargetContext()
        var actualPosition = ""

        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("Expected but not equal to drawable $actualPosition")
            }

            override fun matchesSafely(actual: TextView): Boolean =
                when (drawablePosition) {
                    is DrawablePosition.Start -> {
                        actualPosition = drawablePosition.toString()
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawables[0], context = context)
                    }
                    is DrawablePosition.Top -> {
                        actualPosition = drawablePosition.toString()
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawables[1], context = context)
                    }
                    is DrawablePosition.End -> {
                        actualPosition = drawablePosition.toString()
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawables[2], context = context)
                    }
                    is DrawablePosition.Bottom -> {
                        actualPosition = drawablePosition.toString()
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawables[3], context = context)
                    }
                }
        }
    }
}
