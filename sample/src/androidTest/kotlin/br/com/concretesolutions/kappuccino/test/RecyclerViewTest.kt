package br.com.concretesolutions.kappuccino.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import br.com.concretesolutions.kappuccino.sample.R
import br.com.concretesolutions.kappuccino.sample.recyclerview.RecyclerViewActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<RecyclerViewActivity>(RecyclerViewActivity::class.java, false, true)

    @Test
    fun checkItemVisibility() {
        recyclerView(R.id.recycler_view) {
            atPosition(1) {
                displayed {
                    text("PHP")
                }
            }
        }
    }

    @Test
    fun textInput_andVisibility_check() {
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
    fun swipeToRemove_checkNewSize() {
        val initialSize = RecyclerViewActivity.subjectListSize()

        recyclerView(R.id.recycler_view) {

            sizeIs(initialSize)

            atPosition(0) {
                swipeLeft()
            }

            sizeIs(initialSize - 1)
        }
    }
}
