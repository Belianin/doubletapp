package com.example.doubletapp

import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.util.*

class Habit(val id: Int, var title: String, var description: String?, var priority: Priority, var type: HabitType, var period: HabitPeriod) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString(),
        Priority.valueOf(parcel.readString()!!),
        HabitType.valueOf(parcel.readString()!!),
        parcel.readParcelable(HabitPeriod::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(priority.name)
        parcel.writeString(type.name)
        parcel.writeParcelable(period, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Habit> {
        override fun createFromParcel(parcel: Parcel): Habit {
            return Habit(parcel)
        }

        override fun newArray(size: Int): Array<Habit?> {
            return arrayOfNulls(size)
        }


        private var nextId: Int = 0
        fun getNextId() = nextId++
    }
}

enum class HabitType{
    Good,
    Bad
}

enum class Priority {
    High,
    Normal,
    Low,
}

class HabitPeriod(var count: Int, var type: PeriodType) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        PeriodType.valueOf(parcel.readString()!!)
    )

    enum class PeriodType {
        Day,
        Week
    }

    override fun toString(): String = count.toString() + "раз(а) в " + type.toString()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeString(type.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HabitPeriod> {
        override fun createFromParcel(parcel: Parcel): HabitPeriod {
            return HabitPeriod(parcel)
        }

        override fun newArray(size: Int): Array<HabitPeriod?> {
            return arrayOfNulls(size)
        }
    }
}