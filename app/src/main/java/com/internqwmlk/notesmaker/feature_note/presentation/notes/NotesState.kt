package com.internqwmlk.notesmaker.feature_note.presentation.notes

import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.util.NoteOrder
import com.internqwmlk.notesmaker.feature_note.domain.util.OrderType
import java.util.Collections.emptyList

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

