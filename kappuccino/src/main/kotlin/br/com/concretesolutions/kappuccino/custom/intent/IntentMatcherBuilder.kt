package br.com.concretesolutions.kappuccino.custom.intent

import android.content.Intent
import android.net.Uri
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.hasItem

class IntentMatcherBuilder {
    private val matchList = mutableListOf<Matcher<Intent>>()
    private var result: IntentResultBuilder? = null

    fun className(className: String) {
        matchList.add(hasComponent(className))
    }

    fun action(action: String) {
        matchList.add(hasAction(action))
    }

    fun url(url: String) {
        matchList.add(hasData(Uri.parse(url)))
    }

    fun category(category: String) {
        matchList.add(hasCategories(hasItem(category)))
    }

    fun packageName(packageName: String) {
        matchList.add(toPackage(packageName))
    }

    fun customMatcher(matcher: Matcher<Intent>) {
        matchList.add(matcher)
    }

    fun resultOk() {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).ok()
    }

    fun resultCanceled() {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).canceled()
    }

    fun resultData(data: Intent) {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).data(data)
    }

    fun resultCode(code: Int) {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).code(code)
    }

    internal fun intentMatcher() = this

    internal fun match() = allOf(matchList)

    internal fun result() = result
}

