package com.internqwmlk.notesmaker.feature_note.presentation.notes.components

import android.graphics.Canvas
import android.graphics.drawable.Icon
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.graphics.ColorUtils
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import org.w3c.dom.Text


@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6.copy(
                    color =Color(note.titleColor) ,
                    fontSize = note.titleSize.sp,
                    fontStyle =

                    if(note.titleFontStyle=="FontStyle.Italic"){
                        FontStyle.Italic
                    }
                    else{
                         FontStyle.Normal
                    },
                    fontWeight = note.titleFontWeight,
                    fontFamily = note.titleFontFamily,
                    textDecoration = note.titleTextDecoration,
                ),


                maxLines = 1,
                overflow = TextOverflow.Ellipsis




            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1.copy(
                    color =Color(note.contentColor) ,
                    fontSize = note.contentSize.sp,
                    fontStyle =

                    if(note.contentFontStyle=="FontStyle.Italic"){
                        FontStyle.Italic
                    }
                    else{
                        FontStyle.Normal
                    },
                    fontWeight = note.contentFontWeight,
                    fontFamily = note.contentFontFamily,
                    textDecoration = note.contentTextDecoration,
                ),

                maxLines = 10,
                overflow = TextOverflow.Ellipsis,

            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}