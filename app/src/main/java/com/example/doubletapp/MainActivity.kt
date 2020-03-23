package com.example.doubletapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.core.util.valueIterator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


private val typeString: HashMap<HabitType, String> = HashMap()
private val priorityString: HashMap<Priority, String> = HashMap()

val habits = HashMap<Int, Habit>()
val h1 = Habit(Habit.getNextId(), "Зарядка", "Утреняя разминка", Priority.Normal, HabitType.Good, HabitPeriod(1, HabitPeriod.PeriodType.Day))
val h2 = Habit(Habit.getNextId(), "Пятничный кофе", "Выпить кофе после работы с мужиками", Priority.Low, HabitType.Bad, HabitPeriod(1, HabitPeriod.PeriodType.Week))

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillMaps()

        Log.d("!", "IS INTENT")
        if (intent != null) {
            Log.d("!","NOT NULL 1")
            val newHabit = intent.getParcelableExtra<Habit>("habit")
            if (newHabit != null) {
                Log.d("!", "NOT NULL 2")
                habits[newHabit.id] = newHabit
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HabitAdapter(ArrayList<Habit>(habits.values))

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, HabitActivity::class.java)
              //  .apply { putExtra("habit", habits[0]) }

            startActivity(intent)
        }
    }

    private fun fillMaps() {
        typeString[HabitType.Good] = "Полезная"
        typeString[HabitType.Bad] = "Вредная"

        priorityString[Priority.High] = "Высокий"
        priorityString[Priority.Normal] = "Средний"
        priorityString[Priority.Low] = "Низкий"

        habits[h1.id] = h1
        habits[h2.id] = h2
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
            title.text = habit.title
            description.text = habit.description
            priority.text = priorityString[habit.priority]
            type.text = typeString[habit.type]
            period.text = habit.period.toString()
            //itemView.setBackgroundColor(Color.parseColor("#11FF22"))
        }

    }
}
