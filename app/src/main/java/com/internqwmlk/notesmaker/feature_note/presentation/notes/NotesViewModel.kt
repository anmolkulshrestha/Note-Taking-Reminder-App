package com.internqwmlk.notesmaker.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.use_case.NoteUseCases
import com.internqwmlk.notesmaker.feature_note.domain.util.NoteOrder
import com.internqwmlk.notesmaker.feature_note.domain.util.OrderType
import com.internqwmlk.notesmaker.ui.timelineslider.sliderstate.TimeLineState
import com.internqwmlk.notesmaker.ui.timelineslider.sliderstate.TimeLineStateImpl
import com.internqwmlk.notesmaker.ui.timelineslider.sliderstate.rememberDatePickerState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
):ViewModel() {
    private val _state= mutableStateOf(NotesState())
    val state:State<NotesState> = _state

    private val _DateTimelineState=  mutableStateOf(TimeLineStateImpl(selectedDate = LocalDate.now()))
    val timelinestate:State<TimeLineStateImpl> =  _DateTimelineState

    private var getNotesJob: Job? = null

    private var recentlyDeletedNote: Note?=null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending),timelinestate.value.initialDate.toEpochDay())
    }

    fun onEvent(event:NotesEvent){
        when(event){
            is NotesEvent.onDateSelected->{
             _DateTimelineState.value = TimeLineStateImpl(selectedDate = event.date)
              getNotes(NoteOrder.Date(OrderType.Descending),timelinestate.value.initialDate.toEpochDay())
            }
            is NotesEvent.ToggleOrderSection->{
                _state.value=state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is NotesEvent.DeleteNote->{
                viewModelScope.launch {
                    recentlyDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.Order->{
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder,timelinestate.value.initialDate.toEpochDay())
            }
            is NotesEvent.RestoreNote->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote=null

                }
            }
        }
    }









    private fun getNotes(noteOrder: NoteOrder,date:Long){
        getNotesJob?.cancel()

        getNotesJob=noteUseCases.getNotes(noteOrder,date).onEach {
            notes->
            _state.value=state.value.copy(
                notes=notes,
                noteOrder=noteOrder
            )
        }.launchIn(viewModelScope)


    }
}