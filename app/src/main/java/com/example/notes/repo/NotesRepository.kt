package com.example.notes.repo

import com.example.notes.entities.Note
import androidx.lifecycle.LiveData
import com.example.notes.dao.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun insertNotes(notes: List<Note>) {
        notesDao.insertNotes(notes)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }
}

