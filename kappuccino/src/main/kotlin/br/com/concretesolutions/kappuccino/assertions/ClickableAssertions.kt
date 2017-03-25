package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import org.hamcrest.Matchers.not

class ClickableAssertions(val clickable: Boolean, val scroll: Boolean, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll): ClickableAssertions {
        viewMatcher.id(viewId, scroll).check(clickable())
        return this
    }

    private fun clickable(): ViewAssertion {
        if (clickable) return matches(isClickable())
        else return matches(not(isClickable()))
    }
}

