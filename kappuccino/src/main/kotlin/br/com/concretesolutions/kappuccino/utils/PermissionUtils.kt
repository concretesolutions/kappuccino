package br.com.concretesolutions.kappuccino.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import android.support.v4.content.ContextCompat


fun runtimePermission(permissionNeeded: String, func: PermissionUtils.() -> Unit) {
    PermissionUtils(permissionNeeded).apply { func() }
}

class PermissionUtils(val permissionNeeded: String) {

    private val PERMISSIONS_DIALOG_DELAY = 3000

    fun allow(){
        val ALLOW_BUTTON_INDEX = 1
        handlePermission(ALLOW_BUTTON_INDEX)
    }

    fun deny() {
        val DENY_BUTTON_INDEX = 0
        handlePermission(DENY_BUTTON_INDEX)
    }

    /**
     * From: https://gist.github.com/rocboronat/65b1187a9fca9eabfebb5121d818a3c4
     */
    private fun handlePermission(decision: Int) {
        try {
            val context = InstrumentationRegistry.getTargetContext()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(context,
                permissionNeeded)) {
                sleep(PERMISSIONS_DIALOG_DELAY.toLong())
                val device = UiDevice.getInstance(getInstrumentation())
                val allowPermissions = device.findObject(UiSelector()
                    .clickable(true)
                    .checkable(false)
                    .index(decision))
                if (allowPermissions.exists()) {
                    allowPermissions.click()
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

