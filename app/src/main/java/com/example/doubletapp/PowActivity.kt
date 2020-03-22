package com.example.doubletapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PowActivity : AppCompatActivity() {
    private val COUNT_VAR_NAME = "count"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pow)

        val textView = findViewById<TextView>(R.id.countView)
        val count = intent.getIntExtra(COUNT_VAR_NAME, 0)
        textView.text = (count * count).toString()
    }
}
