package br.com.concretesolutions.kappuccino.custom.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class IntentMatcherBuilder {
    internal var resultCode: Int? = null
    internal var returnData = Intent()

    private var matcher: Matcher<Intent>? = null
    private var className: String? = null
    private var action: String? = null
    private var uri: Uri? = null

    fun className(className: String) {
        this.className = className
    }

    fun action(action: String) {
        this.action = action
    }

    fun url(url: String) {
        this.uri = Uri.parse(url)
    }

    fun resultOk() {
        this.resultCode = Activity.RESULT_OK
    }

    fun resultCanceled() {
        this.resultCode = Activity.RESULT_CANCELED
    }

    fun returnData(data: Intent) {
        returnData = data
    }

    fun customMatcher(matcher: Matcher<Intent>) {
        this.matcher = matcher
    }

    fun getMatcher(): IntentMatcherBuilder {
        return this
    }

    internal fun buildMatcher(): Matcher<Intent> {
        if (matcher != null) return matcher as Matcher<Intent>

        if (className != null)
            matcher = hasComponent(className)
        else
            matcher = allOf(hasAction(action), hasData(uri))
        return matcher as Matcher<Intent>
    }

}

