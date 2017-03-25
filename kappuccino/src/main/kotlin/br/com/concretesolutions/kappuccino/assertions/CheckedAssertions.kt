package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isChecked
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import org.hamcrest.Matchers.not

class CheckedAssertions(val checked: Boolean, val scroll: Boolean, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll): CheckedAssertions {
        viewMatcher.id(viewId, scroll).check(checked())
        return this
    }

    private fun checked(): ViewAssertion {
        if (checked) return matches(isChecked())
        else return matches(not(isChecked()))
    }
}

