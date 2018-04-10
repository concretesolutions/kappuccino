package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.sample.R
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.End
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.Start
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.Top
import br.com.concretesolutions.kappuccino.sample.ManipulateTextActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManipulateTextActivityTest {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<ManipulateTextActivity>(ManipulateTextActivity::class.java, false, true)

    @Test
    fun addCompoundImageTextView() {
        click {
            id(R.id.add_compound_images)
        }
        displayed {
            id(R.id.textView)
            textCompoundDrawable(Start(R.mipmap.ic_launcher))
            textCompoundDrawable(Top(R.mipmap.ic_launcher_round))
            textCompoundDrawable(End(R.drawable.ic_android))
        }
    }
}
