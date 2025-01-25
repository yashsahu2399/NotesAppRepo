package com.example.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteRvItemBinding
import com.example.notes.entities.Note


class NoteAdapter(
    private val notesList: List<Note>,
    private val onNoteClickListener: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: NoteRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            // Set data to views
            binding.tvTitle.text = note.noteTitle
            binding.tvDescription.text = note.noteDescription

            // Set click listener on the item view
            itemView.setOnClickListener {
                // Trigger the click action, passing the clicked note
                onNoteClickListener(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notesList.size
}
