package com.oganbelema.hellocomposev.repository

import com.oganbelema.hellocomposev.dao.NoteDao
import com.oganbelema.hellocomposev.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun addNote(note: Note) = noteDao.insert(note)

    suspend fun updateNote(note: Note) = noteDao.update(note)

    suspend fun deleteNote(note: Note) = noteDao.delete(note)

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

    fun getNote(id: String): Flow<Note> {
        return noteDao.getNoteById(id)
            .flowOn(Dispatchers.IO)
            .conflate()
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
            .flowOn(Dispatchers.IO)
            .conflate()
    }

}