package br.com.concretesolutions.kappuccino.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatDrawableManager

fun getResourceName(context: Context, resourceId: Int) =
        context.resources.getResourceEntryName(resourceId)

fun getDrawable(context: Context, drawableId: Int) =
        AppCompatDrawableManager.get().getDrawable(context, drawableId)


fun getBitmap(drawable: Drawable): Bitmap {
    return if (drawable is BitmapDrawable)
        (drawable).bitmap
    else
        getBitmapFromVectorDrawable(drawable)
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