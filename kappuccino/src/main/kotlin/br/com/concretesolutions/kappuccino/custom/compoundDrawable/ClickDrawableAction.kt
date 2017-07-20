package br.com.concretesolutions.kappuccino.custom.compoundDrawable


import android.graphics.Point
import android.support.annotation.IntDef
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf


class ClickDrawableAction(private @Location val drawableLocation: Int) : ViewAction {

    companion object {
        @IntDef(Left, Top, Right, Bottom)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Location

        const val Left = 0L
        const val Top = 1L
        const val Right = 2L
        const val Bottom = 3L
    }

    override fun getDescription(): String {
        return "click drawable "
    }

    override fun getConstraints(): Matcher<View> {
        return allOf(isAssignableFrom(TextView::class.java), object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun matchesSafely(tv: TextView): Boolean {
                return tv.compoundDrawables[drawableLocation] != null
            }

            override fun describeTo(description: Description) {
                description.appendText("has drawable")
            }
        })
    }

    override fun perform(uiController: UiController?, view: View?) {
        if (view == null)
            return
        val tv = view as TextView
        val drawableBounds = tv.compoundDrawables[drawableLocation].bounds

        val clickPoint = arrayOfNulls<Point>(4)
        clickPoint[Left.toInt()] = Point(tv.left + drawableBounds.width() / 2, (tv.pivotY + drawableBounds.height() / 2) as Int)
        clickPoint[Top.toInt()] = Point((tv.pivotX + drawableBounds.width() / 2) as Int, tv.top + drawableBounds.height() / 2)
        clickPoint[Right.toInt()] = Point(tv.right + drawableBounds.width() / 2, (tv.pivotY + drawableBounds.height() / 2) as Int)
        clickPoint[Bottom.toInt()] = Point((tv.pivotX + drawableBounds.width() / 2) as Int, tv.bottom + drawableBounds.height() / 2)

        if (tv.dispatchTouchEvent(MotionEvent.obtain(android.os.SystemClock.uptimeMillis(), android.os.SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, clickPoint[drawableLocation]!!.x.toFloat(), clickPoint[drawableLocation]!!.y.toFloat(), 0)))
            tv.dispatchTouchEvent(MotionEvent.obtain(android.os.SystemClock.uptimeMillis(), android.os.SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, clickPoint[drawableLocation]!!.x.toFloat(), clickPoint[drawableLocation]!!.y.toFloat(), 0))

    }

}