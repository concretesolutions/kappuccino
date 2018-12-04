package br.com.concretesolutions.kappuccino.custom.link

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.openLinkWithText
import androidx.test.espresso.action.ViewActions.openLinkWithUri
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

class LinkInteractionsMethods(private val linkViewId: Int) {

    fun withText(linkText: String): LinkInteractionsMethods {
        openLink(openLinkWithText(linkText))
        return this
    }

    fun withText(linkTextMatcher: Matcher<String>): LinkInteractionsMethods {
        openLink(openLinkWithText(linkTextMatcher))
        return this
    }

    fun withUri(uriText: String): LinkInteractionsMethods {
        openLink(openLinkWithUri(uriText))
        return this
    }

    fun withUri(uriMatcher: Matcher<Uri>): LinkInteractionsMethods {
        openLink(openLinkWithUri(uriMatcher))
        return this
    }

    private fun openLink(withAction: ViewAction) {
        onView(withId(linkViewId)).perform(withAction)
    }
}