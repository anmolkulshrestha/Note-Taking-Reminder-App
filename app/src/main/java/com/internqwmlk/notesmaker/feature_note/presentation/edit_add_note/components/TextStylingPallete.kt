package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components


import android.graphics.drawable.Icon
import android.inputmethodservice.Keyboard
import android.text.Editable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.AddEditNoteEvent
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.NoteTextFieldState
import com.internqwmlk.notesmaker.ui.AutoResizedText
import com.internqwmlk.notesmaker.ui.theme.BabyBlue
import com.internqwmlk.notesmaker.ui.theme.DarkGray
import com.internqwmlk.notesmaker.ui.theme.PalleteColors
import kotlinx.coroutines.launch
import org.w3c.dom.Text

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextStylingPallete(
    modifier: Modifier=Modifier,
    textStylingPalleteState: TextStylingPalleteState

) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color(26, 32, 46, 255)),
        verticalArrangement = Arrangement
            .SpaceEvenly,
    ) {
        Keyboard.Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text("Text", color = Color.White, fontSize = 24.sp)


        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 12.dp)
        ) {

            Keyboard.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(
                    onClick = {

                        textStylingPalleteState.onFontWeightChange(
                            if (textStylingPalleteState.addEditFieldState.fontWeight == FontWeight.Bold) {
                                FontWeight.Normal
                            } else {
                                FontWeight.Bold
                            }
                        )
                    },
                    Modifier
                        .size(30.dp)

                        .clip(CircleShape)

                ) {
                    Icon(
                        Icons.Default.FormatBold,
                        contentDescription = "Bold Text",
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                2.dp,
                                color = if (textStylingPalleteState.addEditFieldState.fontWeight == FontWeight.Bold) Color.Green else Color.Transparent,
                                shape = CircleShape
                            )

                    )
                }
                IconButton(
                    onClick = {


                        textStylingPalleteState.onFontStyleChange(
                            if (textStylingPalleteState.addEditFieldState.fontstyle == FontStyle.Italic) {
                                FontStyle.Normal
                            } else {
                                FontStyle.Italic
                            }
                        )
                    },
                    Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        Icons.Default.FormatItalic,
                        contentDescription = "Italic Styling",
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                2.dp,
                                color =
                                if (textStylingPalleteState.addEditFieldState.fontstyle == FontStyle.Italic) Color.Green else Color.Transparent,
                                shape = CircleShape
                            )

                    )
                }
                IconButton(
                    onClick = {
                        textStylingPalleteState.onFontDecorationChange(
                            if (textStylingPalleteState.addEditFieldState.textDecoration == TextDecoration.Underline) {
                                TextDecoration.None
                            } else {
                                TextDecoration.Underline
                            }
                        )
                    },
                    Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        Icons.Default.FormatUnderlined,
                        contentDescription = "underlined styling",
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                2.dp,
                                color = if (textStylingPalleteState.addEditFieldState.textDecoration == TextDecoration.Underline) Color.Green else Color.Transparent,
                                shape = CircleShape
                            )
                            .padding(5.dp)

                    )
                }
                IconButton(
                    onClick = { },
                    Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        Icons.Default.StrikethroughS,
                        contentDescription = "strikethrough styling ",
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                2.dp,
                                color = if (textStylingPalleteState.addEditFieldState.textDecoration == TextDecoration.LineThrough) Color.Green else Color.Transparent,
                                shape = CircleShape
                            )

                    )


                }

                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            textStylingPalleteState.onDecrementSize(
                                textStylingPalleteState.addEditFieldState.textSize - 1
                            )
                        },
                        modifier = Modifier.clip(CircleShape)
                            .background(color = BabyBlue, shape = CircleShape).size(30.dp)
                    ) {
                        Icon(Icons.Default.Remove, "minus")
                    }

                    Text(text = textStylingPalleteState.addEditFieldState.textSize.toString())

                    IconButton(
                        onClick = {
                            textStylingPalleteState.onIncreamentSize(
                                textStylingPalleteState.addEditFieldState.textSize + 1
                            )
                        },
                        modifier = Modifier.clip(CircleShape).size(30.dp)
                            .background(color = BabyBlue, shape = CircleShape)
                    ) {

                        Icon(Icons.Default.Add, "Add")

                    }
                }


            }

        }

    }
    Spacer(modifier = Modifier.height(6.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(PalleteColors) { color ->
            val colorInt = color.toArgb()
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(vertical = 5.dp, horizontal = 2.dp)
                    .shadow(15.dp, RoundedCornerShape(5.dp))
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color)
                    .border(
                        width = 3.dp,
                        color = if (textStylingPalleteState.addEditFieldState.color == color) {
                            Color.Green
                        } else Color.Transparent,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable {
                        textStylingPalleteState.onColorChange(color)


                    }

            )
        }

    }
    Spacer(modifier = Modifier.height(12.dp))

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.wrapContentHeight(),

        ) {
        items(listOfFontFamily) { item ->
            BoxWithConstraints(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(2f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(46, 53, 71, 255))
                    .border(
                        2.dp, color = if (
                            textStylingPalleteState.addEditFieldState.fontFamily == item.fontFamily
                        ) {
                            Color.Green
                        } else {
                            Color.Transparent
                        },


                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {

                        textStylingPalleteState.onFontFamilyChange(item.fontFamily) }
                    .padding(4.dp)
            ) {
                AutoResizedText(text = "EasyNotes", item.fontFamily)
            }
        }
    }

}


