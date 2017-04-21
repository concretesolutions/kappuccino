package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.MainActivity
import br.com.concretesolutions.kappuccino.R
import br.com.concretesolutions.kappuccino.newDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, true)

    @Test
    fun checkView() {
        newDisplayed {
            id(R.id.text_hello_world).text(R.string.hello_world)
//            id(R.id.text).text(string.hello_world)
        }
    }

}