package com.example.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.entities.Note


@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note: Note)

    @Insert
    suspend fun insertNotes(notes: List<Note>)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM notesTable ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}

