package com.example.notes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.dao.NotesDao
import com.example.notes.databases.NoteDatabase
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var note: Note
    private lateinit var notesDao: NotesDao // Assuming you're using Room to handle DB operations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Get the note from the intent
        note = intent.getParcelableExtra("note")!!

        // Set the current values in EditText
        binding.editNoteTitle.setText(note.noteTitle)
        binding.editNoteDescription.setText(note.noteDescription)

        // Handle the Save button click
        binding.btnSaveNote.setOnClickListener {
            val updatedTitle = binding.editNoteTitle.text.toString()
            val updatedDescription = binding.editNoteDescription.text.toString()

            // Update the note object with new values
            note.noteTitle = updatedTitle
            note.noteDescription = updatedDescription

            // Call the function to update the note in the database

            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show()
            // Return to the previous activity
            updateNoteInDatabase(note)
            finish()

        }

        binding.btnDelete.setOnClickListener {

            deleteNoteInDatabase(note)
        }
    }

    private fun updateNoteInDatabase(updatedNote: Note) {
        // Assuming you're using Room for the database
        // This would be done asynchronously (e.g., using a coroutine)
        CoroutineScope(Dispatchers.IO).launch {
            notesDao = NoteDatabase.getDatabase(applicationContext).notesDao()
            notesDao.update(updatedNote)

        }
    }

    private fun deleteNoteInDatabase(updatedNote: Note) {
        // Assuming you're using Room for the database
        // This would be done asynchronously (e.g., using a coroutine)
        CoroutineScope(Dispatchers.IO).launch {
            notesDao = NoteDatabase.getDatabase(applicationContext).notesDao()
            notesDao.delete(updatedNote)

        }
    }
}
