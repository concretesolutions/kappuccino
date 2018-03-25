package br.com.concretesolutions.kappuccino.custom.intent

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent

class IntentResultBuilder {
    private var resultCode: Int = Activity.RESULT_OK
    private var resultData = Intent()

    fun ok(): IntentResultBuilder {
        this.resultCode = Activity.RESULT_OK
        return this
    }

    fun canceled(): IntentResultBuilder {
        this.resultCode = Activity.RESULT_CANCELED
        return this
    }

    fun data(data: Intent): IntentResultBuilder {
        resultData = data
        return this
    }

    @Deprecated("Use customCode(code: Int) instead")
    fun code(code: Int): IntentResultBuilder {
        this.resultCode = code
        return this
    }

    fun customCode(code: Int): IntentResultBuilder {
        this.resultCode = code
        return this
    }

    internal fun code() = resultCode

    internal fun data() = resultData

    internal fun build() = Instrumentation.ActivityResult(resultCode, resultData)
}
