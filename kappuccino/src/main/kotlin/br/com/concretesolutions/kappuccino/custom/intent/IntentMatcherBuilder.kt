package br.com.concretesolutions.kappuccino.custom.intent

import android.content.Intent
import android.net.Uri
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasCategories
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.intent.matcher.UriMatchers.hasHost
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
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
        matchList.add(hasData(url))
        return this
    }

    fun url(url: Uri): IntentMatcherBuilder {
        matchList.add(hasData(url))
        return this
    }

    fun url(uriMatcher: Matcher<Uri>): IntentMatcherBuilder {
        matchList.add(hasData(uriMatcher))
        return this
    }

    fun host(host: String): IntentMatcherBuilder {
        matchList.add(hasData(hasHost(equalTo(host))))
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

    infix fun result(func: IntentResultBuilder.() -> IntentResultBuilder): IntentMatcherBuilder {
        if (result == null)
            result = IntentResultBuilder()
        result?.apply { func() }
        return this
    }

    internal fun intentMatcher() = this

    internal fun match() = allOf(matchList)

    internal fun result() = result
}
