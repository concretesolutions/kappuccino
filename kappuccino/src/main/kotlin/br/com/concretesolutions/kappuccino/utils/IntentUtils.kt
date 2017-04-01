package br.com.concretesolutions.kappuccino.utils

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import org.hamcrest.Matcher


fun matchIntent(className: String, func: IntentMatcherSetup.() -> Unit) = IntentMatcherSetup(className).apply{ func() }

class IntentMatcherSetup(className: String) {
    private val matcher: Matcher<Intent> = IntentMatchers.hasComponent(className)
    private val resultIntent = Intent()
    private var resultCode: Int = Activity.RESULT_CANCELED

    fun putReturnObject(): Intent = resultIntent

    fun resultOk() {
        resultCode(Activity.RESULT_OK)
        match()
    }

    fun resultCanceled() {
        resultCode(Activity.RESULT_CANCELED)
        match()
    }

    fun match() {
        val result = ActivityResult(resultCode, resultIntent)
        Intents.intending(matcher).respondWith(result)
    }

    private fun resultCode(resultCode: Int) {
        this.resultCode = resultCode
    }
}
