package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.LinkTextActivity
import br.com.concretesolutions.kappuccino.R
import br.com.concretesolutions.kappuccino.custom.link.openLink
import br.com.concretesolutions.kappuccino.utils.doWait
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LinkTextActivityTest {

    @Rule @JvmField
    var mActivityRule = ActivityTestRule<LinkTextActivity>(LinkTextActivity::class.java, false, true)

    @Test
    fun openFirstLink() {
        doWait(1000L)
        openLink(R.id.txt_link) {
            withText("first link")
        }
        doWait(1000L)
    }

    @Test
    fun openSecondLink() {
        doWait(1000L)
        openLink(R.id.txt_link) {
            withText("second link")
        }
        doWait(3000L)
    }

}