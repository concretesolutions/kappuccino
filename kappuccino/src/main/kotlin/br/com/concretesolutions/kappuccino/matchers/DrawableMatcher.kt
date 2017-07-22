package br.com.concretesolutions.kappuccino.matchers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatDrawableManager
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class DrawableMatcher(private val drawableId: Int) : TypeSafeMatcher<View>(View::class.java) {
    internal var resourceName: String? = null

    override fun matchesSafely(target: View?): Boolean {
        if (target !is ImageView)
            return false

        val currentDrawable = target.drawable ?: return drawableId < 0

        resourceName = getResourceName(target)

        val expectedDrawable = getDrawable(target.context, drawableId) ?: return false

        val currentBitmap = getBitmap(currentDrawable)
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

    private fun getResourceName(target: View) =
            target.context.resources.getResourceEntryName(drawableId)

    private fun getDrawable(context: Context, drawableId: Int) =
            AppCompatDrawableManager.get().getDrawable(context, drawableId)


    private fun getBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable)
            return (drawable).bitmap
        else
            return getBitmapFromVectorDrawable(drawable)
    }

    private fun getBitmapFromVectorDrawable(expectedDrawable: Drawable): Bitmap {
        var drawable: Drawable = expectedDrawable
        if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                drawable.intrinsicHeight, ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}
