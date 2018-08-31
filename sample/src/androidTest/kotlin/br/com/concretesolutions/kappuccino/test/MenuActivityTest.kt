package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.notDisplayed
import br.com.concretesolutions.kappuccino.custom.menu.menu
import br.com.concretesolutions.kappuccino.sample.MenuActivity
import br.com.concretesolutions.kappuccino.sample.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule<MenuActivity>(MenuActivity::class.java, false, true)

    @Test
    fun whenClickingOnItem1_shouldShowCorrectText() {
        menu {
            onItem(R.string.item_1) {
                click()
            }
        }

        displayed {
            text(R.string.item_1_selected)
        }
    }

    @Test
    fun whenClickingOnItem2_shouldShowCorrectText() {
        menu {
            onItem(R.string.item_2) {
                click()
            }
        }

        displayed {
            text(R.string.item_2_selected)
        }
    }

    @Test
    fun whenClickingOnActionBarItem_shouldClearText() {
        menu(openOptionsMenu = false) {
            onActionBarItem(R.id.item_clear) {
                click()
            }
        }

        notDisplayed {
            id(R.id.txt_menu)
        }
    }
}