package com.example.doubletapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import java.util.*

var habits = listOf(
    Habit("Зарядка", "Утреняя разминка", 1, HabitType.Good, Date(0, 0, 1), Color()),
    Habit("Пятничный кофе", "Выпить кофе после работы с мужиками", 2, HabitType.Bad, Date(0, 0, 1), Color()))

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = HabitAdapter(habits)
    }
}

class HabitAdapter(private val habits: List<Habit>) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])
    }

    override fun getItemCount() = habits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.habit_title)
        private val description: TextView = itemView.findViewById(R.id.habit_description)
        private val priority: TextView = itemView.findViewById(R.id.habit_priority)
        private val type: TextView = itemView.findViewById(R.id.habit_type)
        private val period: TextView = itemView.findViewById(R.id.habit_period)

        fun bind(habit: Habit){
            title.text = habit.title;
            description.text = habit.description;
            priority.text = habit.priority.toString();
            type.text = habit.type.toString();
            period.text = habit.period.toString();
            itemView.setBackgroundColor(Color.parseColor("#11FF22"))
        }

    }
}
