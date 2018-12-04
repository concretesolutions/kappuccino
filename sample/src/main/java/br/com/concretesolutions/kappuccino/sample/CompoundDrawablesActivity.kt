package br.com.concretesolutions.kappuccino.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CompoundDrawablesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compound_drawables)
    }

    fun onBtnClick(view: View) {
        (view as Button).setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(this, R.drawable.ic_drawable_start),
                ContextCompat.getDrawable(this, R.drawable.ic_drawable_top),
                ContextCompat.getDrawable(this, R.drawable.ic_drawable_end),
                null)
    }
}