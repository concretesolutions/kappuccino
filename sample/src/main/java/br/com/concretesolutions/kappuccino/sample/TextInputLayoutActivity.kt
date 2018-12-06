package br.com.concretesolutions.kappuccino.sample

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity

class TextInputLayoutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_input_layout)
        putingErrorOnTextInputLayout()
    }

    private fun putingErrorOnTextInputLayout() {
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayoutTest)
        textInputLayout.error = getString(R.string.textInputLayoutError)
    }
}