package com.example.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.entities.Note
import com.example.notes.databases.NoteDatabase
import com.example.notes.repo.NotesRepository

import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<Note>>

    init {
        val notesDao = NoteDatabase.getDatabase(application).notesDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun insertNotes(notes: List<Note>) {
        viewModelScope.launch {
            repository.insertNotes(notes)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            repository.update(note)
        }
    }
}
