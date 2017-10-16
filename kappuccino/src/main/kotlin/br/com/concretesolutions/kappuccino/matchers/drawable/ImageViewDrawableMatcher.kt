package br.com.concretesolutions.kappuccino.matchers.drawable

import android.view.View
import android.widget.ImageView

class ImageViewDrawableMatcher(drawableId: Int) : DrawableMatcher(drawableId) {

    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageView)
            return false
        drawable = target.drawable
        return super.matchesSafely(target)
    }
}