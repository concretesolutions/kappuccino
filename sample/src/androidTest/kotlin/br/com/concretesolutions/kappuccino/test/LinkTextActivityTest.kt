package br.com.concretesolutions.kappuccino.test

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.LinkTextActivity
import br.com.concretesolutions.kappuccino.MainActivity
import br.com.concretesolutions.kappuccino.R
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.matchIntent
import br.com.concretesolutions.kappuccino.custom.link.openLink
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LinkTextActivityTest {

    @Rule @JvmField
    var mActivityRule = ActivityTestRule<LinkTextActivity>(LinkTextActivity::class.java, false, true)

    @Before
    fun initIntents() {
        Intents.init()
    }

    @After
    fun releaseIntents() {
        Intents.release()
    }

    @Test
    fun openFirstLink() {

        matchIntent {
            className(MainActivity::class.java.name)
            result {
                ok()
            }
        }

        openLink(R.id.txt_link) {
            withText("first link")
        }

        matchIntent {
            className(MainActivity::class.java.name)
        }
    }

    @Test
    fun openSecondLink() {
        matchIntent {
            action(Intent.ACTION_VIEW)
            url("https://www.google.com")
            result {
                ok()
            }
        }

        openLink(R.id.txt_link) {
            withText("second link")
        }

        matchIntent {
            action(Intent.ACTION_VIEW)
            url("https://www.google.com")
        }
    }

}