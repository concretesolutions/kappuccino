package br.com.concretesolutions.kappuccino.custom.menu

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

fun menu(openOptionsMenu: Boolean = true, func: MenuMethods.() -> MenuMethods): MenuMethods {
    return MenuMethods().apply {
        if (openOptionsMenu)
            openOptionsMenu()
        func()
    }
}

class MenuMethods {

    fun openOptionsMenu(): MenuMethods {
        openActionBarOverflowOrOptionsMenu(getTargetContext())
        return this
    }

    fun onItem(@StringRes itemTextId: Int, func: Interactions.() -> Interactions): MenuMethods {
        return createInteraction(onView(withText(itemTextId)), func)
    }

    fun onItem(itemText: String, func: Interactions.() -> Interactions): MenuMethods {
        return createInteraction(onView(withText(itemText)), func)
    }

    fun onActionBarItem(@IdRes itemId: Int, func: Interactions.() -> Interactions): MenuMethods {
        return createInteraction(onView(withId(itemId)), func)
    }

    private fun createInteraction(viewInteraction: ViewInteraction,
                                  func: Interactions.() -> Interactions): MenuMethods {
        Interactions(viewInteraction).apply { func() }
        return this
    }

    class Interactions(private val viewInteraction: ViewInteraction) {

        fun click(): Interactions {
            viewInteraction.perform(ViewActions.click())
            return this
        }

        fun longClick(): Interactions {
            viewInteraction.perform(ViewActions.longClick())
            return this
        }
    }
}