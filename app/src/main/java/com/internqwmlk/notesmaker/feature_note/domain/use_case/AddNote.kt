package com.internqwmlk.notesmaker.feature_note.domain.use_case

import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend  operator  fun invoke(note:Note){
        if(note.title.isBlank()) {
            throw Exception("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw Exception("The content of the note can't be empty.")
        }
        noteRepository.insertNote(note)

    }


}