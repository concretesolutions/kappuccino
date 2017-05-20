package br.com.concretesolutions.kappuccino

import android.view.View
import br.com.concretesolutions.kappuccino.extensions.scroll

class BaseViewInteractions(val scroll: Boolean = false, val matchers: List<org.hamcrest.Matcher<View>>) {

    fun check(assertion: android.support.test.espresso.ViewAssertion) {
        for (matcher in matchers)
            android.support.test.espresso.Espresso.onView(matcher).scroll(scroll).check(assertion)
    }

    fun action(viewAction: android.support.test.espresso.ViewAction) {
        for (matcher in matchers)
            android.support.test.espresso.Espresso.onView(matcher).scroll(scroll).perform(viewAction)
    }
}