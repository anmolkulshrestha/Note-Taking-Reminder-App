package com.internqwmlk.notesmaker.feature_note.data.data_source

import androidx.room.*
import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE timeStamp = :date")
    fun getNotes(date:Long): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM alarm_table WHERE alarmId = :id")
    suspend fun getAlarmById(id: Long): AlarmNote?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarmNote: AlarmNote):Long


    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM alarm_table ")
    fun getAlarms():Flow<List<AlarmNote>>


}