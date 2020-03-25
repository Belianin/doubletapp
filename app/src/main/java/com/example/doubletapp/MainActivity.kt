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
import androidx.core.content.ContextCompat.startActivity
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


public val typeString: HashMap<HabitType, String> = HashMap()
public val stringType: HashMap<String, HabitType> = HashMap()
public val priorityString: HashMap<Priority, String> = HashMap()
public val stringPriority: HashMap<String, Priority> = HashMap()
val periodTypeString: HashMap<HabitPeriod.PeriodType, String> = HashMap()
val stringPeriodType: HashMap<String, HabitPeriod.PeriodType> = HashMap()

val habits = HashMap<Int, Habit>()
val h1 = Habit(Habit.getNextId(), "Зарядка", "Утреняя разминка", Priority.Normal, HabitType.Good, HabitPeriod(1, HabitPeriod.PeriodType.Day))
val h2 = Habit(Habit.getNextId(), "Пятничный кофе", "Выпить кофе после работы с мужиками", Priority.Low, HabitType.Bad, HabitPeriod(1, HabitPeriod.PeriodType.Week))

var currentHabit: Habit? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillMaps()

        if (intent != null) {
            val newHabit = intent.getParcelableExtra<Habit>("habit")
            if (newHabit != null) {
                habits[newHabit.id] = newHabit
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HabitAdapter(ArrayList<Habit>(habits.values))

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, HabitActivity::class.java)
            if (currentHabit != null)
                intent.apply { putExtra("habit", habits[0]) }

            startActivity(intent)
        }
    }

    private fun fillMaps() {
        typeString[HabitType.Good] = "Полезная"
        typeString[HabitType.Bad] = "Вредная"

        stringType["Полезная"] = HabitType.Good
        stringType["Вредная"] = HabitType.Bad

        priorityString[Priority.High] = "Высокий"
        priorityString[Priority.Normal] = "Средний"
        priorityString[Priority.Low] = "Низкий"

        stringPriority["Высокий"] = Priority.High
        stringPriority["Средний"] = Priority.Normal
        stringPriority["Низкий"] = Priority.Low

        periodTypeString[HabitPeriod.PeriodType.Day] = "день"
        periodTypeString[HabitPeriod.PeriodType.Week] = "неделю"

        stringPeriodType["неделю"] = HabitPeriod.PeriodType.Week
        stringPeriodType["день"] = HabitPeriod.PeriodType.Day

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

            itemView.setOnClickListener {
                currentHabit = habit
            }
        }
    }
}
