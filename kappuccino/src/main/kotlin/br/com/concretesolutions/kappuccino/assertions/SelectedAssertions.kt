package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isSelected
import br.com.concretesolutions.kappuccino.BaseViewMatchers
import org.hamcrest.Matchers.not

class SelectedAssertions(val selected: Boolean, val scroll: Boolean, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll): SelectedAssertions {
        viewMatcher.id(viewId, scroll).check(selectable())
        return this
    }

    private fun selectable(): ViewAssertion {
        if (selected) return matches(isSelected())
        else return matches(not(isSelected()))
    }
}

