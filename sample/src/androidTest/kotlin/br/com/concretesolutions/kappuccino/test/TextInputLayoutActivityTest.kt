package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.custom.textInputLayout.textInputLayout
import br.com.concretesolutions.kappuccino.sample.R
import br.com.concretesolutions.kappuccino.sample.TextInputLayoutActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextInputLayoutActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<TextInputLayoutActivity>(TextInputLayoutActivity::class.java, false, true)

    @Test
    fun checkIfHasErrorOnTextInputLayout() {
        textInputLayout(R.id.textInputLayoutTest) {
            hasTextError()
        }
    }

    @Test
    fun checkTextErrorOnTextInputLayoutWithText() {
        textInputLayout(R.id.textInputLayoutTest) {
            withTextError("This is an Error")
        }
    }

    @Test
    fun checkTextErrorOnTextInputLayoutWithStringResource() {
        textInputLayout(R.id.textInputLayoutTest) {
            withTextError(R.string.textInputLayoutError)
        }
    }
}