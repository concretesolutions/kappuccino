package br.com.concretesolutions.kappuccino.custom.intent

import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import java.lang.Exception
import kotlin.test.fail

object IntentMatcherInteractions {
    fun matchIntent(func: IntentMatcherBuilder.() -> Unit) {
        val intentMatcherObject = IntentMatcherBuilder().apply { func() }.intentMatcher()
        val result = intentMatcherObject.result()

        try {
            if (result == null)
                intended(intentMatcherObject.match())
            else
                intending(intentMatcherObject.match()).respondWith(result.build())
        } catch (exception: Exception) {
            if (exception is NullPointerException)
                fail("Don't forge to call Intents.init() before and Intents.release() after")
            exception.printStackTrace()
        }
    }
}

