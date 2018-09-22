package br.com.concretesolutions.kappuccino.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat

fun getResourceName(context: Context, resourceId: Int): String =
        context.resources.getResourceEntryName(resourceId)

fun getDrawable(context: Context, drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawableId)
}

fun getBitmap(drawable: Drawable): Bitmap {
    return if (drawable is BitmapDrawable)
        (drawable).bitmap
    else
        getBitmapFromVectorDrawable(drawable)
}

/**
 * Compare drawables's [Drawable.ConstantState] and also [Bitmap]s from those drawables
 * Only compares the [Drawable.ConstantState] can ended up in a possible false negative as stated here:
 *
 * https://stackoverflow.com/questions/9125229/comparing-two-drawables-in-android/31562099#31562099
 *
 * @param expected Expected Drawable to be matched
 * @param actual Actual Drawable
 *
 * @return True if they are identical, false otherwise.
 */
fun compareDrawables(expected: Drawable, actual: Drawable): Boolean {
    val expectedState = expected.constantState
    val actualState = actual.constantState
    return ((expectedState != null && actualState != null && expectedState == actualState)
            || getBitmap(expected).sameAs(getBitmap(actual)))
}

fun checkResIdAgainstDrawable(@DrawableRes resId: Int, drawable: Drawable?, context: Context): Boolean {

    val expected = getDrawable(context, resId) ?: return false

    return drawable != null && compareDrawables(expected, drawable)
}

fun getBitmapFromVectorDrawable(vectorDrawable: Drawable): Bitmap {
    var drawable: Drawable = vectorDrawable
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable).mutate()
    }

    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}