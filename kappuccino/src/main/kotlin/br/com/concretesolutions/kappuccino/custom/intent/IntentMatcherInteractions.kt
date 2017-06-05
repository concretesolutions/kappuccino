package br.com.concretesolutions.kappuccino.custom.intent

import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import kotlin.test.fail

object IntentMatcherInteractions {
    fun matchIntent(func: IntentMatcherBuilder.() -> Unit) {
        val intentMatcherObject = IntentMatcherBuilder().apply { func() }.intentMatcher()
        intendMatcher(intentMatcherObject)
    }

    private fun intendMatcher(intentMatcherObject: IntentMatcherBuilder) {
        val result = intentMatcherObject.result()
        try {
            if (result == null)
                intended(intentMatcherObject.match())
            else
                intending(intentMatcherObject.match()).respondWith(result.build())
        } catch (exception: NullPointerException) {
            fail("Don't forge to call Intents.init() before and Intents.release() after")
        }
    }
}

