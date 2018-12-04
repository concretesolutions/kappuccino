package br.com.concretesolutions.kappuccino

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
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