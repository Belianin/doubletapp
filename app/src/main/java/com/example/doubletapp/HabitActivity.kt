package com.example.doubletapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val baseHabit = intent.getParcelableExtra("habit") as Habit?
            ?: Habit(Habit.getNextId(), "", "", Priority.Normal, HabitType.Good, HabitPeriod(1, HabitPeriod.PeriodType.Day))

        val titleInput: EditText = findViewById(R.id.habit_title_input)
        titleInput.setText(baseHabit.title)

        val descriptionInput: EditText = findViewById(R.id.habit_description_input)
        descriptionInput.setText(baseHabit.description)

        val priorityInput: Spinner = findViewById(R.id.habit_priority_input)
        priorityInput.setSelection(getPriorityIndex(priorityInput, baseHabit.priority))

        val typeInput: RadioGroup = findViewById(R.id.habit_type_input)
        if (baseHabit.type == HabitType.Bad)
            typeInput.check(R.id.habit_type_input_bad)

        val periodTypeInput: Spinner = findViewById(R.id.period_type_input)
        periodTypeInput.setSelection(getPeriodTypeIndex(periodTypeInput, baseHabit.period.type))

        val periodCountInput: EditText = findViewById(R.id.period_count_input);
        periodCountInput.setText(baseHabit.period.count.toString())

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val habit = Habit(
                baseHabit.id,
                titleInput.text.toString(),
                descriptionInput.text.toString(),
                stringPriority[priorityInput.selectedItem as String]!!,
                getType(typeInput),
                HabitPeriod(periodCountInput.text.toString().toInt(), stringPeriodType[periodTypeInput.selectedItem as String]!!)
            )
            val intent = Intent(this, MainActivity::class.java)
              .apply { putExtra("habit", habit) }

            startActivity(intent)
        }
    }

    private fun getPriorityIndex(spinner: Spinner, priority: Priority): Int {
        val value = priorityString[priority]
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).equals(value))
                return i
        }

        return 0
    }

    private fun getPeriodTypeIndex(spinner: Spinner, type: HabitPeriod.PeriodType): Int {
        val value = periodTypeString[type]
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).equals(value))
                return i
        }

        return 0
    }

    private fun getType(radioGroup: RadioGroup): HabitType {
        if (radioGroup.checkedRadioButtonId == R.id.habit_type_input_good)
            return HabitType.Good
        return HabitType.Bad
    }
}
