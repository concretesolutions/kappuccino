package br.com.concretesolutions.kappuccino.test

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.actions.TextActions.typeText
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.sentIntent
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.stubIntent
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import br.com.concretesolutions.kappuccino.sample.MainActivity
import br.com.concretesolutions.kappuccino.sample.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, true)

    @Test
    fun typeText_edit_text() {
        typeText("This is a text to be typed") {
            allOf {
                id(R.id.edit_hello_world)
                parent {
                    id(R.id.main_parent)
                }
            }
        }

        displayed {
            text("This is a text to be typed")
        }
    }

    @Test
    fun recyclerView_test() {
        recyclerView(R.id.recycler_view) {
            atPosition(1) {
                displayed {
                    text("PHP")
                }
            }
        }
    }

    @Test
    fun recyclerView_textInput_test() {
        recyclerView(R.id.recycler_view) {
            atPosition(0) {
                typeText(R.id.subject_edittext, "Position 0")
                displayed {
                    text("Position 0")
                }
            }

            atPosition(1) {
                typeText(R.id.subject_edittext, "Position 1")
                displayed {
                    text("Position 1")
                }
            }

            atPosition(2) {
                typeText(R.id.subject_edittext, "Position 2")
                displayed {
                    text("Position 2")
                }
            }
        }
    }

    @Test
    fun whenSwipingAnItem_shouldRemoveFromRecyclerView() {
        recyclerView(R.id.recycler_view) {
            sizeIs(11)

            atPosition(0) {
                swipeLeft()
            }

            sizeIs(10)
        }
    }

    @Test
    fun intentMatcherTest() {
        val whatsPackageName = "com.whatsapp"
        val playStoreUrl = "https://play.google.com/store/apps/details?id="
        stubIntent {
            action(Intent.ACTION_VIEW)
            url(playStoreUrl + whatsPackageName)
            respondWith {
                ok()
            }
        }

        click {
            id(R.id.btn_start_activity)
        }

        sentIntent {
            action(Intent.ACTION_VIEW)
            url(playStoreUrl + whatsPackageName)
        }
    }

    @Test
    fun vectorDrawableTest() {
        displayed {
            background(R.drawable.ic_android)
        }

        displayed {
            allOf {
                id(R.id.view_background_2)
                background(R.drawable.ic_android)
            }
        }
    }

    @Test
    fun colorDrawableTest() {
        displayed {
            allOf {
                id(R.id.btn_start_activity)
                background(R.color.colorAccent)
            }
        }

        displayed {
            background(R.color.colorAccent)
        }
    }

    @Test
    fun bitmapDrawableTest() {
        displayed {
            allOf {
                id(R.id.view_background)
                background(R.mipmap.ic_launcher_round)
            }
        }
    }

    @Before
    fun initIntents() {
        Intents.init()
    }

    @After
    fun releaseIntents() {
        Intents.release()
    }
}