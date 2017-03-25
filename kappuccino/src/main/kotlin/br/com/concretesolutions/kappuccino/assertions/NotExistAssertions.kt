package br.com.concretesolutions.kappuccino.assertions

import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import br.com.concretesolutions.kappuccino.BaseViewMatchers


class NotExistAssertions(val scroll: Boolean, vararg parentId: Int? = arrayOf<Int>(), descendantId: Int?, descendantText: Int?) {

    val viewMatcher = BaseViewMatchers(scroll, parentId = *parentId, descendantId = descendantId, descendantText = descendantText)

    fun id(viewId: Int, scroll: Boolean = this.scroll): NotExistAssertions {
        viewMatcher.id(viewId, scroll).check(doesNotExist())
        return this
    }

    fun text(textId: Int, scroll: Boolean = this.scroll): NotExistAssertions {
        viewMatcher.text(textId, scroll).check(doesNotExist())
        return this
    }

    fun text(text: String, scroll: Boolean = this.scroll): NotExistAssertions {
        viewMatcher.text(text, scroll).check(doesNotExist())
        return this
    }

    fun contentDescription(contentDescriptionId: Int, scroll: Boolean = this.scroll): NotExistAssertions {
        viewMatcher.contentDescription(contentDescriptionId, scroll).check(doesNotExist())
        return this
    }

    fun contentDescription(contentDescriptionText: String = "", scroll: Boolean = this.scroll): NotExistAssertions {
        viewMatcher.contentDescription(contentDescriptionText, scroll).check(doesNotExist())
        return this
    }

}
