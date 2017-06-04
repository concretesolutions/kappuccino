package br.com.concretesolutions.kappuccino.custom.intent

import android.app.Instrumentation.ActivityResult
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import java.lang.Exception
import kotlin.test.fail

object IntentMatcherInteractions {
    fun matchIntent(func: IntentMatcherBuilder.() -> Unit) {
        val intentMatcher = IntentMatcherBuilder().apply { func() }.getMatcher()
        val matcher = intentMatcher.buildMatcher()
        try {
            if (intentMatcher.resultCode == null)
                intended(matcher)
            else {
                val result = ActivityResult(intentMatcher.resultCode as Int, intentMatcher.returnData)
                intending(matcher).respondWith(result)
            }
        } catch (exception: Exception) {
            if (exception is NullPointerException)
                fail("Don't forge to call Intents.init() before and Intents.release() after")
            else
                exception.printStackTrace()
        }
    }
}

