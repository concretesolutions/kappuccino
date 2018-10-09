package br.com.concretesolutions.kappuccino.sample.recyclerview

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import br.com.concretesolutions.kappuccino.sample.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewActivityTest {

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
                typeText(R.id.subject_edit_text, "Position 0")
                displayed {
                    text("Position 0")
                    text("ANDROID")
                }
            }

            atPosition(2) {
                typeText(R.id.subject_edit_text, "Position 2")
                displayed {
                    text("Position 2")
                }
            }
        }
    }

    @Test
    fun notDisplayed_issue99() {
        // This test will check that if the EditText for the item at position 1 is not displayed by the user.
        recyclerView(R.id.recycler_view) {
            atPosition(0, 2, 3) {
                displayedChildView(R.id.subject_edit_text)
            }
            atPosition(1) {
                notDisplayedChildView(R.id.subject_edit_text)
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