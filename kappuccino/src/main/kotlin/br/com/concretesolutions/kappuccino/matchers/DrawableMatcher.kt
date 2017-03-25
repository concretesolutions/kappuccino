package br.com.concretesolutions.kappuccino.matchers

import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatDrawableManager
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class DrawableMatcher(private val expectedId: Int) : TypeSafeMatcher<View>(View::class.java) {
    internal var resourceName: String? = null

    override fun matchesSafely(target: View?): Boolean {
        if (target !is ImageView) {
            return false
        }
        val imageView: ImageView = target
        if (expectedId < 0) {
            return imageView.drawable == null
        }
        val resources = target.getContext().resources
        val expectedDrawable = AppCompatDrawableManager.get().getDrawable(target.getContext(), expectedId)

        resourceName = resources.getResourceEntryName(expectedId)

        if (expectedDrawable == null) {
            return false
        }

        val bitmap: Bitmap
        val otherBitmap: Bitmap

        if (imageView.drawable is BitmapDrawable) {
            bitmap = (imageView.drawable as BitmapDrawable).bitmap
        } else {
            bitmap = getBitmapFromVectorDrawable(imageView.drawable)
        }

        if (expectedDrawable is BitmapDrawable) {
            otherBitmap = (expectedDrawable).bitmap
        } else {
            otherBitmap = getBitmapFromVectorDrawable(expectedDrawable)
        }

        return bitmap.sameAs(otherBitmap)
    }

    fun getBitmapFromVectorDrawable(expectedDrawable: Drawable): Bitmap {
        var drawable: Drawable = expectedDrawable
        if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                drawable.intrinsicHeight, ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)

        return bitmap
    }




    override fun describeTo(description: Description?) {
        description?.appendText("with drawable from resource id: ")
        description?.appendValue(expectedId)
        if (resourceName != null) {
            description?.appendText("[")
            description?.appendText(resourceName)
            description?.appendText("]")
        }
    }
}
