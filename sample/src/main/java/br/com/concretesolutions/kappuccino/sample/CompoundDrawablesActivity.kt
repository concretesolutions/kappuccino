package br.com.concretesolutions.kappuccino.sample

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

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