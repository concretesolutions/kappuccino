package br.com.concretesolutions.kappuccino.matchers.drawable

import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorRes
import android.view.View
import br.com.concretesolutions.kappuccino.utils.getDrawable
import br.com.concretesolutions.kappuccino.utils.getResourceName
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ColorDrawableBackgroundMatcher(@ColorRes private val colorId: Int) : TypeSafeMatcher<View>(View::class.java) {

    private var resourceName: String? = null

    override fun matchesSafely(target: View): Boolean {
        if (target.background !is ColorDrawable)
            return false
        val currentColor: ColorDrawable = target.background as ColorDrawable

        resourceName = getResourceName(target.context, colorId)

        val expectedColor: ColorDrawable = getDrawable(target.context, colorId) as ColorDrawable? ?: return false
        return currentColor.color == expectedColor.color
    }

    override fun describeTo(description: Description?) {
        description?.appendText("with background color from resource id: ")
        description?.appendValue(colorId)
        if (resourceName != null) {
            description?.appendText("[")
            description?.appendText(resourceName)
            description?.appendText("]")
        }
    }
}