package br.com.concretesolutions.kappuccino.custom.intent

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import kotlin.test.fail

object IntentMatcherInteractions {

    fun sentIntent(func: IntentMatcherBuilder.() -> IntentMatcherBuilder) {
        val intentMatcherObject = IntentMatcherBuilder().apply { func() }.intentMatcher()
        try {
            intended(intentMatcherObject.match())
        } catch (exception: NullPointerException) {
            fail("Don't forget to call Intents.init() before and Intents.release() after")
        }
    }

    fun stubIntent(func: IntentMatcherBuilder.() -> IntentMatcherBuilder) {
        val intentMatcherObject = IntentMatcherBuilder().apply { func() }.intentMatcher()
        val result = intentMatcherObject.result()
        try {
            if (result == null)
                fail ("No result set for the stubIntent method")
            intending(intentMatcherObject.match()).respondWith(result.build())
        } catch (exception: NullPointerException) {
            fail("Don't forget to call Intents.init() before and Intents.release() after")
        }
    }

    @Deprecated("Use sentIntent or stubIntent instead")
    fun matchIntent(func: IntentMatcherBuilder.() -> IntentMatcherBuilder) {
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
            fail("Don't forget to call Intents.init() before and Intents.release() after")
        }
    }
}
