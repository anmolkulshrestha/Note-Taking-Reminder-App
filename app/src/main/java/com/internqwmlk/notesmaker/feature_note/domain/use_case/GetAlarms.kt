package com.internqwmlk.notesmaker.feature_note.domain.use_case

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


class GetAlarms(private val repository: NoteRepository) {

     operator fun invoke(): Flow<List<AlarmNote>> {
        return  repository.getAlarms().map {
            it.filter { alarmNote -> alarmNote.time>= LocalDateTime.of(LocalDate.now(),
            LocalTime.now()).atZone(
            ZoneId.systemDefault()).toEpochSecond()*1000 }
        }

    }


}