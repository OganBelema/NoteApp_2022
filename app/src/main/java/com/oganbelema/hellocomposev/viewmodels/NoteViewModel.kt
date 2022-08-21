package com.oganbelema.hellocomposev.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oganbelema.hellocomposev.model.Note
import com.oganbelema.hellocomposev.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()

    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { notes ->
                    _noteList.value = notes
                }
        }
    }

    fun getNote(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNote(id).distinctUntilChanged()
                .collect { note ->
                    _note.value = note
                }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun deleteAllNote() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

}