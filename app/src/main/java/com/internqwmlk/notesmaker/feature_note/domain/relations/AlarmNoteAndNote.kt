package com.internqwmlk.notesmaker.feature_note.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note


data class AlarmNoteAndNote(
    @Embedded
    val note:Note,
    @Relation(
        parentColumn = "alarmId",
        entityColumn = "alarmId"
    )
    val alarm: AlarmNote
)

