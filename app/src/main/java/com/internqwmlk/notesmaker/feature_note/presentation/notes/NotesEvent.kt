package com.internqwmlk.notesmaker.feature_note.presentation.notes

import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.util.NoteOrder
import java.time.LocalDate

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data class onDateSelected(val date:LocalDate):NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()

}