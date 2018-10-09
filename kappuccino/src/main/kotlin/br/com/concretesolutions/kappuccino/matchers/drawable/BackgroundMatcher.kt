package br.com.concretesolutions.kappuccino.matchers.drawable

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import br.com.concretesolutions.kappuccino.utils.getBitmap
import br.com.concretesolutions.kappuccino.utils.getBitmapFromVectorDrawable
import br.com.concretesolutions.kappuccino.utils.getDrawable
import br.com.concretesolutions.kappuccino.utils.getResourceName
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class BackgroundMatcher(private val drawableId: Int) : TypeSafeMatcher<View>(View::class.java) {

    private var resourceName: String? = null
    private var typeName: String = ""

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun matchesSafely(target: View): Boolean {
        val context = target.context
        resourceName = getResourceName(context, drawableId)

        val currentBackground = target.background ?: return drawableId < 0

        return when (currentBackground) {
            is VectorDrawable -> {
                typeName = VECTOR
                matchVector(context, currentBackground, drawableId)
            }

            is ColorDrawable -> {
                typeName = COLOR
                matchColor(context, currentBackground, drawableId)
            }

            is BitmapDrawable -> {
                typeName = BITMAP
                matchBitmap(context, currentBackground, drawableId)
            }

            else -> false
        }
    }

    override fun describeTo(description: Description?) {
        description?.appendText("with $typeName " +
                "drawable from resource id: $drawableId")
        if (resourceName != null)
            description?.appendText(" [$resourceName]")
    }

    companion object {
        private const val VECTOR = "vector"
        private const val BITMAP = "bitmap"
        private const val COLOR = "color"

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        private fun matchVector(context: Context, current: Drawable, drawableId: Int): Boolean {
            val currentBitmap = getBitmapFromVectorDrawable(current)
            val expectedDrawable = getDrawable(context, drawableId)

            // Do not replace with Elvis operator because it cast the variable from Drawable to VectorDrawable
            @Suppress("FoldInitializerAndIfToElvis")
            if (expectedDrawable !is VectorDrawable)
                return false

            val expectedBitmap = getBitmap(expectedDrawable)
            return currentBitmap.sameAs(expectedBitmap)
        }

        private fun matchColor(context: Context, current: ColorDrawable, colorId: Int): Boolean {
            val expectedColor = getDrawable(context, colorId) as? ColorDrawable ?: return false
            return current.color == expectedColor.color
        }

        private fun matchBitmap(context: Context, current: BitmapDrawable, drawableId: Int): Boolean {
            val expectedDrawable = getDrawable(context, drawableId) as? BitmapDrawable ?: return false
            val expectedBitmap = getBitmap(expectedDrawable)
            return current.bitmap.sameAs(expectedBitmap)
        }
    }
}