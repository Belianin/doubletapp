package com.example.doubletapp

import android.graphics.Color
import java.util.*

class Habit(var title: String, var description: String, var priority: Int, var type: HabitType, var period: Date, var color: Color)

enum class HabitType{
    Good,
    Bad
}