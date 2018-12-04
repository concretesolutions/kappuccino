package br.com.concretesolutions.kappuccino.test

import androidx.test.espresso.intent.Intents
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.notDisplayed
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.sentIntent
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.stubIntent
import br.com.concretesolutions.kappuccino.sample.MainActivity
import br.com.concretesolutions.kappuccino.sample.R
import br.com.concretesolutions.kappuccino.sample.VisibilityAssertionsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VisibilityActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<VisibilityAssertionsActivity>(VisibilityAssertionsActivity::class.java, false, true)

    @Test
    fun match_textView_withId() {
        displayed {
            id(R.id.txt_visible)
        }
    }

    @Test
    fun match_textView_withText() {
        displayed {
            text("This is a visible TextView without id")
        }
    }

    @Test
    fun match_textView_withIdAndText() {
        displayed {
            allOf {
                id(R.id.txt_visible)
                text("This is a visible TextView with id")
            }
        }
    }

    @Test
    fun match_invisible_textView_withId() {
        notDisplayed {
            id(R.id.txt_invisible)
        }
    }

    @Test
    fun match_imageView_withContentDescription() {
        displayed {
            contentDescription("This is a content description")
        }
    }

    @Test
    fun match_textView_withParent() {
        displayed {
            allOf {
                parent {
                    id(R.id.parent1)
                }
                id(R.id.txt_inside_parent)
            }

            allOf {
                parent {
                    id(R.id.parent2)
                }
                id(R.id.txt_inside_parent)
            }
        }
    }

    @Test
    fun match_multiple_views_inside_parent() {
        displayed {
            parent(R.id.parent3) {
                id(R.id.txt_inside_parent1)
                text("This is another textview inside a parent")
                id(R.id.txt_inside_parent3)
            }
        }
    }

    @Test
    fun match_imageView_withImage() {
        displayed {
            image(R.mipmap.ic_launcher_round)
        }
    }

    @Test
    fun match_textView_withTextColor() {
        displayed {
            allOf {
                id(R.id.txt_text_color)
                textColor(R.color.colorAccent)
            }
        }
    }

    @Test
    fun match_intentTo_mainActivity() {
        Intents.init()

        stubIntent {
            className(MainActivity::class.java.name)
            respondWith {
                ok()
            }
        }

        click {
            id(R.id.btn_to_main)
        }

        sentIntent {
            className(MainActivity::class.java.name)
        }

        Intents.release()
    }
}
