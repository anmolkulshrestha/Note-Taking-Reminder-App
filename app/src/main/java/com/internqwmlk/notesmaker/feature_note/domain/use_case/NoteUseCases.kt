package com.internqwmlk.notesmaker.feature_note.domain.use_case



data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val addALarm:AddAlarm,
    val getAlarm:GetAlarm,
    val getAlarms:GetAlarms
)