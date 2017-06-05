package br.com.concretesolutions.kappuccino.custom.intent

import android.app.Instrumentation.ActivityResult
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
            else {
                val activityResult = ActivityResult(result.code(), result.data())
                intending(intentMatcherObject.match()).respondWith(activityResult)
            }
        } catch (exception: Exception) {
            if (exception is NullPointerException)
                fail("Don't forge to call Intents.init() before and Intents.release() after")
            else
                exception.printStackTrace()
        }
    }
}

