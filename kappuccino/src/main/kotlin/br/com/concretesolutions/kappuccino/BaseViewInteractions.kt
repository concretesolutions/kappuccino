package br.com.concretesolutions.kappuccino

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.view.View
import br.com.concretesolutions.kappuccino.extensions.scroll
import org.hamcrest.Matcher

class BaseViewInteractions(val scroll: Boolean = false, val matchers: List<Matcher<View>>) {

    fun check(assertion: ViewAssertion) {
        for (matcher in matchers)
            onView(matcher).scroll(scroll).check(assertion)
    }

    fun action(viewAction: ViewAction) {
        for (matcher in matchers)
            onView(matcher).scroll(scroll).perform(viewAction)
    }
}