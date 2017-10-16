package br.com.concretesolutions.kappuccino.matchers.drawable

import android.graphics.drawable.Drawable
import android.view.View
import br.com.concretesolutions.kappuccino.utils.getBitmap
import br.com.concretesolutions.kappuccino.utils.getDrawable
import br.com.concretesolutions.kappuccino.utils.getResourceName
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

open class DrawableMatcher(private val drawableId: Int) : TypeSafeMatcher<View>(View::class.java) {
    private var resourceName: String? = null
    protected var drawable: Drawable? = null

    override fun matchesSafely(target: View): Boolean {
        if (drawable == null) return drawableId < 0

        val currentDrawable = drawable

        resourceName = getResourceName(target.context, drawableId)

        val expectedDrawable = getDrawable(target.context, drawableId) ?: return false
        val currentBitmap = getBitmap(currentDrawable!!)
        val expectedBitmap = getBitmap(expectedDrawable)

        return currentBitmap.sameAs(expectedBitmap)
    }

    override fun describeTo(description: Description?) {
        description?.appendText("with drawable from resource id: ")
        description?.appendValue(drawableId)
        if (resourceName != null) {
            description?.appendText("[")
            description?.appendText(resourceName)
            description?.appendText("]")
        }
    }
}
