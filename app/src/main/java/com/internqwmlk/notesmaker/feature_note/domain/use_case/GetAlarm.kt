package com.internqwmlk.notesmaker.feature_note.domain.use_case

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository

class GetAlarm(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id:Long): AlarmNote? {
        return  noteRepository.getAlarmById(id)

    }
}