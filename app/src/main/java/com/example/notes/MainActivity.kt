package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.adapters.NoteAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.entities.Note
import com.example.notes.viewmodels.NotesViewModel


class MainActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter(emptyList()) { note ->
            // Handle note click (update or delete)

            val message = "Title: ${ note.noteTitle}\nDescription: ${note.noteDescription}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        }

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = noteAdapter



        // Observe LiveData from ViewModel
        notesViewModel.allNotes.observe(this) { notes->
            noteAdapter = NoteAdapter(notes) { note ->
                // Handle note click (update or delete)

                openEditNoteActivity(note)
            }
            binding.rvNotes.adapter = noteAdapter
             // In MainActivity after saving the updated note

        }

        // Add a new note (for example purpose)
        binding.btnAddNote.setOnClickListener {
            val note = Note(
                noteTitle = binding.editTextTitle.text.toString(),
                noteDescription = binding.editText.text.toString(),
                timeStamp = System.currentTimeMillis().toString() )
                binding.editText.setText("")
                binding.editTextTitle.setText("")

            notesViewModel.insert(note)
        }

    }
    private fun openEditNoteActivity(note: Note) {
        // Start EditNoteActivity and pass the selected note
        val intent = Intent(this, EditNoteActivity::class.java)
        intent.putExtra("note", note) // Pass the note object
        startActivity(intent)
    }

}
