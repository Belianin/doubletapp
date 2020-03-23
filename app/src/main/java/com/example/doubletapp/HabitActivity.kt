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

        val habit = intent.getParcelableExtra("habit") as Habit?
            ?: Habit("", "", Priority.Normal, HabitType.Good, HabitPeriod(1, HabitPeriod.PeriodType.Day))

        val titleInput: EditText = findViewById(R.id.habit_title_input)
        titleInput.setText(habit.title)

        val descriptionTitle: EditText = findViewById(R.id.habit_description_input)
        descriptionTitle.setText(habit.description)

        val priorityTitle: Spinner = findViewById(R.id.habit_priority_input)
    }
}
