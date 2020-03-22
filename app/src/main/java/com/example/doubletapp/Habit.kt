package com.example.doubletapp

import android.graphics.Color
import android.os.Bundle
import java.util.*

class Habit(var title: String, var description: String, var priority: Int, var type: HabitType, var period: Date, var color: Color) {

    companion object {

        fun createBundle(habit: Habit) : Bundle {
            val bundle = Bundle()
            bundle.putString("title", habit.title)
            bundle.putString("description", habit.description)
            bundle.putInt("priority", habit.priority)

            return bundle
        }

        fun fromBundle(bundle: Bundle): Habit {
            return Habit(
                bundle.getString("title", null),
                bundle.getString("description", null),
                bundle.getInt("priority", 0),
                HabitType.Good,
                Date(0, 0,0),
                Color()
            )
        }
    }
}

enum class HabitType{
    Good,
    Bad
}