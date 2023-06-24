package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note

import android.graphics.fonts.FontFamily
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TextStylingPalleteState
import java.time.LocalTime

sealed class AddEditNoteEvent{
    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class EnteredContent(val value: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    data class SaveNote(val noteDate:Long): AddEditNoteEvent()
    data class setAlarm(val time:LocalTime):AddEditNoteEvent()
    data class changeTitleColor(val color: Color):AddEditNoteEvent()
    data class changeContentColor(val color: Color):AddEditNoteEvent()

    data class changeTitleFontStyle(val fontStyle: FontStyle):AddEditNoteEvent()
    data class changeContentFontStyle(val fontStyle: FontStyle):AddEditNoteEvent()
    data class changeTitleFontWeight(val fontWeight: FontWeight):AddEditNoteEvent()
    data class changeContentFontWeight(val fontWeight: FontWeight):AddEditNoteEvent()
    data class changeTitleTextDecoration(val textDecoration: TextDecoration):AddEditNoteEvent()
    data class changeContentTextDecoration(val textDecoration: TextDecoration):AddEditNoteEvent()
    data class changeTitleFontFamily(val fontFamily: FontFamily):AddEditNoteEvent()
    data class changeContentFontFamily(val fontFamily: FontFamily):AddEditNoteEvent()
    data class changeTextStylePalleteSatate(val textStylingPalleteState: TextStylingPalleteState):AddEditNoteEvent()
    data class incrementTitleTextSize(val size:Int):AddEditNoteEvent()
    data class decremnetTitleTextSize(val size:Int):AddEditNoteEvent()
    data class incrementContentTextSize(val size:Int):AddEditNoteEvent()
    data class decrementContentTextSize(val size:Int):AddEditNoteEvent()

}