package com.internqwmlk.notesmaker.feature_note.domain.use_case

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository

class AddAlarm(private val noteRepository: NoteRepository) {

    suspend  operator  fun invoke(alarm: AlarmNote):Long{

      return  noteRepository.insertAlarm(alarm)

    }


}