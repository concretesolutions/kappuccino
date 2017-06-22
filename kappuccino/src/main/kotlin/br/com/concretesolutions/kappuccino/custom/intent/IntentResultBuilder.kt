package br.com.concretesolutions.kappuccino.custom.intent

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent

class IntentResultBuilder {
    private var resultCode: Int = Activity.RESULT_OK
    private var resultData = Intent()

    internal fun ok() {
        this.resultCode = Activity.RESULT_OK
    }

    internal fun canceled() {
        this.resultCode = Activity.RESULT_CANCELED
    }

    internal fun data(data: Intent) {
        resultData = data
    }

    internal fun code(code: Int) {
        this.resultCode = code
    }

    internal fun code() = resultCode

    internal fun data() = resultData

    internal fun build() = Instrumentation.ActivityResult(resultCode, resultData)
}

