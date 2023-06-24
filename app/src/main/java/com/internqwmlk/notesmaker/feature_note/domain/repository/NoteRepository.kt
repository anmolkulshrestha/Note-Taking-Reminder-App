package com.internqwmlk.notesmaker.feature_note.domain.repository

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(date:Long): Flow<List<Note>>

    fun getAlarms():Flow<List<AlarmNote>>

    suspend fun insertNote(note: Note)

    suspend fun insertAlarm(alarm: AlarmNote):Long

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id:Int):Note?

    suspend fun getAlarmById(id:Long):AlarmNote?


}