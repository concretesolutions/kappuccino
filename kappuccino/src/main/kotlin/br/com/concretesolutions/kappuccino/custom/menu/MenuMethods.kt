package br.com.concretesolutions.kappuccino.custom.menu

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

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