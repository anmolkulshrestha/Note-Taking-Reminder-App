package com.internqwmlk.notesmaker.di

import android.app.Application
import androidx.room.Room
import com.internqwmlk.notesmaker.feature_alarm.AlarmScheduler
import com.internqwmlk.notesmaker.feature_alarm.AlarmSchedulerImpl

import com.internqwmlk.notesmaker.feature_note.data.data_source.NoteDatabase
import com.internqwmlk.notesmaker.feature_note.data.repository.NoteRepositoryImpl
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository
import com.internqwmlk.notesmaker.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(

            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            addALarm = AddAlarm(repository),
            getAlarm = GetAlarm(repository),
            getAlarms = GetAlarms(repository)
        )
    }
     @Provides
     @Singleton
    fun provideAlarmScheduler(app: Application):AlarmScheduler{
        return AlarmSchedulerImpl(app)
    }
}