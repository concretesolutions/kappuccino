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

    fun className(className: String): IntentMatcherBuilder {
        matchList.add(hasComponent(className))
        return this
    }

    fun action(action: String): IntentMatcherBuilder {
        matchList.add(hasAction(action))
        return this
    }

    fun url(url: String): IntentMatcherBuilder {
        matchList.add(hasData(Uri.parse(url)))
        return this
    }

    fun category(category: String): IntentMatcherBuilder {
        matchList.add(hasCategories(hasItem(category)))
        return this
    }

    fun packageName(packageName: String): IntentMatcherBuilder {
        matchList.add(toPackage(packageName))
        return this
    }

    fun customMatcher(matcher: Matcher<Intent>): IntentMatcherBuilder {
        matchList.add(matcher)
        return this
    }

    fun resultOk(): IntentMatcherBuilder {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).ok()
        return this
    }

    fun resultCanceled(): IntentMatcherBuilder {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).canceled()
        return this
    }

    fun resultData(data: Intent): IntentMatcherBuilder {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).data(data)
        return this
    }

    fun resultCode(code: Int): IntentMatcherBuilder {
        if (result == null)
            result = IntentResultBuilder()
        (result as IntentResultBuilder).code(code)
        return this
    }

    internal fun intentMatcher() = this

    internal fun match() = allOf(matchList)

    internal fun result() = result
}

