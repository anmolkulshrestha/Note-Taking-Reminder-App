package com.internqwmlk.notesmaker.feature_note.data.data_source


import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note


@Database(
    entities = [Note::class,AlarmNote::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}