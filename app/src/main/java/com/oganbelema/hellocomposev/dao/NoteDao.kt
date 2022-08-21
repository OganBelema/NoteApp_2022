package com.oganbelema.hellocomposev.dao

import androidx.room.*
import com.oganbelema.hellocomposev.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id=:id")
    fun getNoteById(id: String)

    @Delete
    fun delete(note: Note)
}
