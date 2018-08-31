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

        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("Expected but not equal to drawable $drawablePosition")
            }

            override fun matchesSafely(actual: TextView): Boolean {
                return when (drawablePosition) {
                    is DrawablePosition.Start -> {
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawablesRelative[0], context)
                    }
                    is DrawablePosition.Top -> {
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawablesRelative[1], context)
                    }
                    is DrawablePosition.End -> {
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawablesRelative[2], context)
                    }
                    is DrawablePosition.Bottom -> {
                        checkResIdAgainstDrawable(drawablePosition.resId, actual.compoundDrawablesRelative[3], context)
                    }
                }
            }
        }
    }
}