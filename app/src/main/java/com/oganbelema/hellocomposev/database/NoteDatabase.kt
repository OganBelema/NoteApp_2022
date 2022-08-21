package com.oganbelema.hellocomposev.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oganbelema.hellocomposev.dao.NoteDao
import com.oganbelema.hellocomposev.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}