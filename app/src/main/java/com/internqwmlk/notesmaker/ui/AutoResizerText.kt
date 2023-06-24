package com.internqwmlk.notesmaker.ui

import android.graphics.fonts.FontFamily
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.fontFamilySelectionItemTile
import org.w3c.dom.Text
import java.time.format.TextStyle

@Composable
fun AutoResizedText(
    text: String,
    fontFamily: FontFamily,
    style: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier,

    ) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    val defaultFontSize = 20.sp

    Text(
        text = text,
        color = Color.White,
        fontFamily=fontFamily,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = false,

        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}