package br.com.concretesolutions.kappuccino.matchers.drawable

import android.graphics.drawable.VectorDrawable
import android.view.View
import org.hamcrest.Description

class VectorDrawableBackgroundMatcher(drawableId: Int): DrawableMatcher(drawableId) {

    override fun matchesSafely(target: View): Boolean {
        if (target.background !is VectorDrawable)
            return false
        drawable = target.background
        return super.matchesSafely(target)
    }

    override fun describeTo(description: Description?) {
        description?.appendText("with background drawable from resource id: ")
        description?.appendValue(drawableId)
        if (resourceName != null) {
            description?.appendText("[")
            description?.appendText(resourceName)
            description?.appendText("]")
        }
    }

}