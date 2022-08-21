package com.oganbelema.hellocomposev.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oganbelema.hellocomposev.converter.DateConverter
import com.oganbelema.hellocomposev.converter.UUIDConverter
import com.oganbelema.hellocomposev.dao.NoteDao
import com.oganbelema.hellocomposev.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}