package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note

import android.app.Application
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.toArgb

import androidx.compose.ui.text.font.FontStyle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.internqwmlk.notesmaker.feature_alarm.AlarmScheduler

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.use_case.NoteUseCases

import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TextStylingPalleteState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.*
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val alarmScheduler: AlarmScheduler,

    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _alarmTime= mutableStateOf(LocalTime.of(3,3,3,33))
    val  alarmTime:State<LocalTime> = _alarmTime

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."

    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _textStylingPalleteState = mutableStateOf(TextStylingPalleteState())
    val textStylingPalleteState: State<TextStylingPalleteState> = _textStylingPalleteState

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter some content"
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent
    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null
    private var currentAlarmId:Long?=null

    init {

        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentAlarmId=note.alarmId
                        if(currentAlarmId!=null){
                            _alarmTime.value= LocalDateTime.ofInstant(Instant.ofEpochMilli(noteUseCases.getAlarm(currentAlarmId!!)?.time!!),
                                ZoneId.systemDefault()).toLocalTime()


                        }
                        currentNoteId = note.id
                        _noteTitle.value = _noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false,
                            textSize = note.titleSize,
                            fontWeight = note.titleFontWeight,
                            fontstyle =
                            if(note.titleFontStyle=="FontStyle.Italic"){
                                 FontStyle.Italic
                            }
                            else{
                                FontStyle.Normal
                            },
                            textDecoration = note.titleTextDecoration,
                            fontFamily = note.titleFontFamily,
                            color = Color(note.titleColor),
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false,
                            fontFamily = note.contentFontFamily,
                            textSize = note.contentSize,
                            fontWeight = note.contentFontWeight,
                            fontstyle = if(note.contentFontStyle=="FontStyle.Italic"){
                                FontStyle.Italic
                            }
                            else{
                                FontStyle.Normal
                            },
                            textDecoration = note.contentTextDecoration,
                            color = Color(note.contentColor)

                        )
                        _noteColor.value = note.color


                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.changeTextStylePalleteSatate->{
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    event.textStylingPalleteState.addEditFieldState,
                    event.textStylingPalleteState.onColorChange,
                    event.textStylingPalleteState.onFontStyleChange,
                    event.textStylingPalleteState.onFontWeightChange,
                    event.textStylingPalleteState.onFontDecorationChange,
                    event.textStylingPalleteState.onFontFamilyChange,
                    event.textStylingPalleteState.onIncreamentSize,
                    event.textStylingPalleteState.onDecrementSize
                )


            }

            is AddEditNoteEvent.incrementContentTextSize->{
                _noteContent.value=_noteContent.value.copy(
                    textSize = event.size
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )
            }
            is AddEditNoteEvent.incrementTitleTextSize->{
                _noteTitle.value=_noteTitle.value.copy(
                    textSize = event.size
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )
            }
            is AddEditNoteEvent.decrementContentTextSize->{
                _noteContent.value=_noteContent.value.copy(
                    textSize = event.size
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )
            }
            is AddEditNoteEvent.decremnetTitleTextSize->{
                _noteTitle.value=_noteTitle.value.copy(
                    textSize = event.size
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )
            }
            is AddEditNoteEvent.changeContentFontFamily->{

                _noteContent.value= _noteContent.value.copy(
                    fontFamily = event.fontFamily
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )

            }
            is AddEditNoteEvent.changeTitleFontFamily->{


                _noteTitle.value= _noteTitle.value.copy(
                    fontFamily = event.fontFamily
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )

            }

            is AddEditNoteEvent.changeTitleFontStyle->{
                _noteTitle.value=_noteTitle.value.copy(
                    fontstyle = event.fontStyle
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )
            }
            is AddEditNoteEvent.changeContentFontStyle->{
                _noteContent.value=_noteContent.value.copy(
                    fontstyle = event.fontStyle
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )
            }
            is AddEditNoteEvent.changeContentFontWeight->{
                _noteContent.value=_noteContent.value.copy(
                    fontWeight = event.fontWeight
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )
            }
            is AddEditNoteEvent.changeTitleFontWeight->{
                _noteTitle.value=_noteTitle.value.copy(
                  fontWeight = event.fontWeight
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )
            }
            is AddEditNoteEvent.changeTitleTextDecoration->{
                _noteTitle.value=_noteTitle.value.copy(
                   textDecoration = event.textDecoration
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )

            }
            is AddEditNoteEvent.changeContentTextDecoration->{
                _noteContent.value=_noteContent.value.copy(
                    textDecoration = event.textDecoration
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )

            }

            is AddEditNoteEvent.changeContentColor->{

                _noteContent.value=_noteContent.value.copy(
                    color = event.color
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteContent.value
                )
            }
            is AddEditNoteEvent.changeTitleColor->{
                _noteTitle.value=_noteTitle.value.copy(
                    color = event.color
                )
                _textStylingPalleteState.value=_textStylingPalleteState.value.copy(
                    addEditFieldState = _noteTitle.value
                )
            }
            is AddEditNoteEvent.setAlarm->{
                _alarmTime.value=event.time
             //   alarmScheduler.addAlarm(AlarmNote(LocalDateTime.of(LocalDate.now(),event.time),noteTitle.value.text))
            }
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {

                viewModelScope.launch {
                    try {
                        if(alarmTime.value!=LocalTime.of(3,3,3,33)){
                            var id =   noteUseCases.addALarm(AlarmNote(LocalDateTime.of(LocalDate.ofEpochDay(event.noteDate),alarmTime.value).atZone(ZoneId.systemDefault()).toEpochSecond()*1000,noteTitle.value.text, alarmId = currentAlarmId))
                            currentAlarmId=id
                            alarmScheduler.addAlarm(AlarmNote(LocalDateTime.of(LocalDate.ofEpochDay(event.noteDate),alarmTime.value).atZone(
                                ZoneId.systemDefault()).toEpochSecond()*1000,noteTitle.value.text, alarmId = currentAlarmId))
                        }

                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timeStamp = event.noteDate,
                                color = noteColor.value,
                                id = currentNoteId,
                                alarmId = currentAlarmId,
                                titleColor = noteTitle.value.color.toArgb(),
                                contentColor = noteContent.value.color.toArgb(),
                                titleFontFamily = noteTitle.value.fontFamily,
                                contentFontFamily = noteContent.value.fontFamily,
                                titleSize = noteTitle.value.textSize,
                                contentSize = noteContent.value.textSize,
                                titleFontStyle =
                                if(noteTitle.value.fontstyle==FontStyle.Italic){
                                    "FontStyle.Italic"
                                }
                                else{
                                    "FontStyle.Normal"
                                },
                                contentFontStyle = if(noteContent.value.fontstyle==FontStyle.Italic){
                                    "FontStyle.Italic"
                                }
                                else{
                                    "FontStyle.Normal"
                                },
                                titleFontWeight = noteTitle.value.fontWeight,
                                contentFontWeight = noteContent.value.fontWeight,
                                titleTextDecoration = noteTitle.value.textDecoration,
                                contentTextDecoration = noteContent.value.textDecoration,

                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)

                    } catch(e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}
fun Color.toHexCode(): String {
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255
    return String.format("#%02x%02x%02x", red.toInt(), green.toInt(), blue.toInt())
}