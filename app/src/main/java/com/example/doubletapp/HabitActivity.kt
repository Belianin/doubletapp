package com.example.doubletapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class HabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val habit = intent.getParcelableExtra("habit") as Habit?
            ?: Habit(Habit.getNextId(), "", "", Priority.Normal, HabitType.Good, HabitPeriod(1, HabitPeriod.PeriodType.Day))

        val titleInput: EditText = findViewById(R.id.habit_title_input)
        titleInput.setText(habit.title)

        val descriptionTitle: EditText = findViewById(R.id.habit_description_input)
        descriptionTitle.setText(habit.description)

        val priorityTitle: Spinner = findViewById(R.id.habit_priority_input)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
              .apply { putExtra("habit", habit) }

            startActivity(intent)
        }
    }
}
