package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note


import android.graphics.fonts.FontFamily
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

import com.internqwmlk.notesmaker.ui.theme.DarkGray
import kotlinx.coroutines.Dispatchers.Default

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val color:Color= DarkGray,
    val textSize:Int=18,
    val fontstyle:FontStyle=FontStyle.Normal,
    val fontWeight:FontWeight=FontWeight.Normal,
    val textDecoration: TextDecoration=TextDecoration.None,
    val fontFamily: FontFamily =FontFamily.Default
)