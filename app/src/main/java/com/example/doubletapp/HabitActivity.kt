package com.example.doubletapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class HabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val bundle = intent.getBundleExtra("habit")
        val habit = if (bundle == null) {
            Habit("", "", 0, HabitType.Good, Date(0, 0, 1), Color())
        } else {
            Habit.fromBundle(bundle)
        }

        val textView = findViewById<TextView>(R.id.countView)

        textView.text = (habit.title).toString()

        val titleInput: EditText = findViewById(R.id.habit_title_input)
        titleInput.setText(habit.title)

        val priorityTitle: Spinner = findViewById(R.id.habit_priority_input)
    }
}
