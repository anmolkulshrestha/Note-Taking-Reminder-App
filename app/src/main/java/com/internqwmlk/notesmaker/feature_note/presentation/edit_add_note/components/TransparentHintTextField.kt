package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components


import android.graphics.drawable.Icon
import android.graphics.fonts.FontFamily
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internqwmlk.notesmaker.ui.theme.DarkGray
import kotlinx.coroutines.Dispatchers.Default
import org.w3c.dom.Text
import java.time.format.TextStyle

@Composable
fun TransparentHintTextField(

    text: String,
    onTextStylingClick:()->Unit,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    textColor:Color= DarkGray,
    textSize:Int=18,
    fontStyle:FontStyle=FontStyle.Normal,
    singleLine: Boolean = false,
    fontWeight: FontWeight=FontWeight.Normal,
    fontFamily: FontFamily =FontFamily.Default,
    textDecoration: TextDecoration=TextDecoration.None,
    onFocusChange: (FocusState) -> Unit
) {
    Keyboard.Row()
    {
        Box(
            modifier = Modifier.weight(5f)
        ) {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,


                textStyle = textStyle.copy(
                    color = textColor,
                    fontSize = textSize.sp,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    textDecoration = textDecoration,
                    fontFamily = fontFamily


                ),


                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    }
            )
            if (isHintVisible) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = androidx.compose.ui.graphics.Color.DarkGray
                )
            }

        }



        IconButton(onClick = onTextStylingClick, modifier = Modifier.weight(1f)) {
            Icon(
                Icons.Default.TextFormat,
                contentDescription = "TextStyling",
                tint = androidx.compose.ui.graphics.Color.Black
            )
        }
    }


}


