package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components

import android.graphics.Color
import android.graphics.fonts.FontFamily
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.NoteTextFieldState

data class TextStylingPalleteState(
   val addEditFieldState: NoteTextFieldState=NoteTextFieldState(),
   val onColorChange:(color:androidx.compose.ui.graphics.Color)->Unit={},
   val onFontStyleChange:(fontStyle:FontStyle)->Unit={},
   val onFontWeightChange:(fontWeight:FontWeight)->Unit={},
   val onFontDecorationChange:(textDecoration:TextDecoration)->Unit={},
   val onFontFamilyChange:(fontFamily: FontFamily)->Unit={},
   val onIncreamentSize:(size:Int)->Unit={},
   val onDecrementSize:(size:Int)->Unit={},
   )