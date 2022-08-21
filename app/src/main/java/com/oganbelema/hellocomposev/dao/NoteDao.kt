package com.oganbelema.hellocomposev.dao

import androidx.room.*
import com.oganbelema.hellocomposev.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getNoteById(id: String): Note

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE from note")
    suspend fun deleteAllNotes()
}
