package br.com.concretesolutions.kappuccino.custom.runtimePermission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import android.support.v4.content.ContextCompat

/**
 * This method allows you to check if your app behaves as expected when denying or allowing the permission requested.
 * For example:
 *
 * - If after denying the permission, your app displays a message or dialog to the user.
 * - If after allowing the permission, your app displays a message or dialog to the user.
 *
 * In case you just want to avoid the permission dialogs to execute your tests, it would be a good idea to use
 * [GrantPermissionRule](https://developer.android.com/reference/android/support/test/rule/GrantPermissionRule)
 *
 * @param permissionNeeded  The [android.Manifest.permission] or [android.Manifest.permission_group] value corresponding
 *                          to the requested permission.
 * @param action            The action [RuntimePermissionHandler.allow] or [RuntimePermissionHandler.deny]
 *                          on the current permission request dialog.
 */
fun runtimePermission(permissionNeeded: String, action: RuntimePermissionHandler.() -> Unit) {
    RuntimePermissionHandler(permissionNeeded).apply { action() }
}

class RuntimePermissionHandler(private val permissionNeeded: String) {

    private companion object {
        private const val PERMISSIONS_DIALOG_DELAY = 500L
        private const val ALLOW_BUTTON_ID = "com.android.packageinstaller:id/permission_allow_button"
        private const val DENY_BUTTON_ID = "com.android.packageinstaller:id/permission_deny_button"
    }

    /**
     * Click on ALLOW button from current permission request dialog
     */
    fun allow() {
        handlePermission(ALLOW_BUTTON_ID)
    }

    /**
     * Click on DENY button from current permission request dialog
     */
    fun deny() {
        handlePermission(DENY_BUTTON_ID)
    }

    /**
     * This method will check the Android version before handling the permission. It will also properly handle the
     * permission based on action from [allow] or [deny] methods.
     *
     * @param actionButtonId The id of the action button
     *
     * From: https://gist.github.com/rocboronat/65b1187a9fca9eabfebb5121d818a3c4
     */
    private fun handlePermission(actionButtonId: String) {

        try {
            val context = InstrumentationRegistry.getTargetContext()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && !hasNeededPermission(context, permissionNeeded)) {

                // This sleeping is just to make sure that we wait time enough for the dialog be displayed.
                sleep(PERMISSIONS_DIALOG_DELAY)

                // Finding the correct button to perform the click action
                val device = UiDevice.getInstance(getInstrumentation())
                val allowPermissions = device.findObject(UiSelector()
                        .clickable(true)
                        .checkable(false)
                        .resourceId(actionButtonId))

                if (allowPermissions.exists()) {
                    allowPermissions.click()
                } else {
                    println("Could not find button with Id: $actionButtonId")
                }
            }
        } catch (e: UiObjectNotFoundException) {
            println("There is no permissions dialog to interact with")
        }
    }

    private fun hasNeededPermission(context: Context, permissionNeeded: String): Boolean {
        val permissionStatus = ContextCompat.checkSelfPermission(context, permissionNeeded)
        return permissionStatus == PackageManager.PERMISSION_GRANTED
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            throw RuntimeException("Cannot execute Thread.sleep()")
        }
    }
}