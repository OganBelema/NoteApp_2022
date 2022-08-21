package com.oganbelema.hellocomposev.converter

import androidx.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromDateToTimeStamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDateFromTimeStamp(timeStamp: Long): Date {
        return Date(timeStamp)
    }
}