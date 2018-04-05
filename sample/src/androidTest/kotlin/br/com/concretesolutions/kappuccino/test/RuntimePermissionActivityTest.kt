package br.com.concretesolutions.kappuccino.test

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.kappuccino.R
import br.com.concretesolutions.kappuccino.RuntimePermissionActivity
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.utils.doWait
import br.com.concretesolutions.kappuccino.utils.runtimePermission
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class RuntimePermissionActivityTest {

    /**
     * To test permissions, you will have to uninstall the app before run the tests,
     * or remove the permissions that are already granted
     */

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule<RuntimePermissionActivity>(RuntimePermissionActivity::class.java, false, true)

    @Test
    fun A_denyPermission() {
        click {
            id(R.id.btn_request_permission)
        }

        doWait(500L)
        runtimePermission(Manifest.permission.READ_CONTACTS) {
            deny()
        }

        doWait(500L)
        displayed {
            text("PERMISSION DENIED")
        }
    }

    @Test
    fun B_grantPermission() {
        click {
            id(R.id.btn_request_permission)
        }

        doWait(500L)
        runtimePermission(Manifest.permission.READ_CONTACTS) {
            grant()
        }

        doWait(500L)
        displayed {
            text("PERMISSION GRANTED")
        }
    }
}