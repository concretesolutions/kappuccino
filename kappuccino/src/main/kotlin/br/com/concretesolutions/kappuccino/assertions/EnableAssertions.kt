package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import org.hamcrest.Matchers.not

class EnableAssertions(val enabled: Boolean, val scroll: Boolean, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll): EnableAssertions {
        viewMatcher.id(viewId, scroll).check(enabled())
        return this
    }

    private fun enabled(): ViewAssertion {
        if (enabled) return matches(isEnabled())
        else return matches(not(isEnabled()))
    }
}

