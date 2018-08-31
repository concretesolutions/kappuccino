package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.End
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.Start
import br.com.concretesolutions.kappuccino.matchers.drawable.DrawablePosition.Top
import br.com.concretesolutions.kappuccino.sample.CompoundDrawablesActivity
import br.com.concretesolutions.kappuccino.sample.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CompoundDrawablesActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<CompoundDrawablesActivity>(CompoundDrawablesActivity::class.java, false, true)

    @Test
    fun addCompoundImageTextView() {
        click {
            id(R.id.add_compound_images_button)
        }

        displayed {
            textCompoundDrawable(Start(R.drawable.ic_drawable_start))
            textCompoundDrawable(Top(R.drawable.ic_drawable_top))
            textCompoundDrawable(End(R.drawable.ic_drawable_end))
        }
    }
}