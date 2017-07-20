package br.com.concretesolutions.kappuccino.custom.compoundDrawable


import android.graphics.Point
import android.graphics.Rect
import android.os.SystemClock.uptimeMillis
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

/**
 * Based on answer https://stackoverflow.com/a/37032927/3279958
 */

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

        val clickPoint = createClickPoints(tv, drawableBounds)

        if (clickAction(MotionEvent.ACTION_DOWN, tv, clickPoint))
            clickAction(MotionEvent.ACTION_UP, tv, clickPoint)
    }

    private fun createClickPoints(tv: TextView, bounds: Rect): Array<Point?> {
        val clickPoint = arrayOfNulls<Point>(4)
        clickPoint[Left.toInt()] = PointCreator(bounds).left(tv)
        clickPoint[Right.toInt()] = PointCreator(bounds).right(tv)
        clickPoint[Top.toInt()] = PointCreator(bounds).top(tv)
        clickPoint[Bottom.toInt()] = PointCreator(bounds).bottom(tv)
        return clickPoint
    }

    private fun clickAction(action: Int, tv: TextView, clickPoint: Array<Point?>) =
            tv.dispatchTouchEvent(MotionEvent.obtain(uptimeMillis(), uptimeMillis(), action, clickPoint[drawableLocation]!!.x.toFloat(), clickPoint[drawableLocation]!!.y.toFloat(), 0))

    private class PointCreator(private val bounds: Rect) {

        fun left(textView: TextView) = Point(fromWidth(textView.left), fromHeight(textView.pivotY.toInt()))

        fun right(textView: TextView) = Point(fromWidth(textView.right), fromHeight(textView.pivotY.toInt()))

        fun top(textView: TextView) = Point(fromWidth(textView.pivotX.toInt()), fromHeight(textView.top))

        fun bottom(textView: TextView) = Point(fromWidth(textView.pivotX.toInt()), fromHeight(textView.bottom))

        private fun fromWidth(fromPosition: Int): Int {
            return fromPosition + bounds.width() / 2
        }

        private fun fromHeight(fromPosition: Int): Int {
            return fromPosition + bounds.height() / 2
        }

    }
}