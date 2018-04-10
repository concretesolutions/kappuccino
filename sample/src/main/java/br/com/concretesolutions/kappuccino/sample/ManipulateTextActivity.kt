package br.com.concretesolutions.kappuccino.sample
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class ManipulateTextActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maniuplate_text)
        textView = findViewById(R.id.textView)
    }

    fun onBtnClick(view: View) {
        (view as Button).setCompoundDrawables(
                ContextCompat.getDrawable(this, R.mipmap.ic_launcher),
                ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round),
                ContextCompat.getDrawable(this, R.drawable.ic_android),
                null)
    }
}
