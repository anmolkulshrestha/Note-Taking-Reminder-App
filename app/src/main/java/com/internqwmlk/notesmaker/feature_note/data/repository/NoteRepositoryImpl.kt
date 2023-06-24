package com.internqwmlk.notesmaker.feature_note.data.repository

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.data.data_source.NoteDao
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl( private val dao: NoteDao):NoteRepository {
    override fun getNotes(date:Long): Flow<List<Note>> {
       return dao.getNotes(date)

    }

    override suspend fun insertNote(note: Note) {
           dao.insertNote(note)
    }

    override suspend fun insertAlarm(alarm: AlarmNote):Long {
        return dao.insertAlarm(alarm)
    }

    override suspend fun deleteNote(note: Note) {
     dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
     return  dao.getNoteById(id)
    }

    override suspend fun getAlarmById(id: Long): AlarmNote? {
        return dao.getAlarmById(id)
    }

    override fun getAlarms(): Flow<List<AlarmNote>> {
        return dao.getAlarms()
    }
}