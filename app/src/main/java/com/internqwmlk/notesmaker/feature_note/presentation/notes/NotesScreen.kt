package com.internqwmlk.notesmaker.feature_note.presentation.notes


import android.Manifest
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.inputmethodservice.Keyboard
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.internqwmlk.notesmaker.feature_note.presentation.notes.components.OrderSection
import kotlinx.coroutines.launch

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Alarm

import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.ContextCompat
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.AddEditNoteEvent
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.NoteTextFieldState
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TextStylingPallete
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.fontFamilySelectionItem
import com.internqwmlk.notesmaker.feature_note.presentation.notes.components.NoteItem
import com.internqwmlk.notesmaker.feature_note.presentation.utils.Screen
import com.internqwmlk.notesmaker.ui.theme.*
import com.internqwmlk.notesmaker.ui.timelineslider.DatePickerTimeline
import com.internqwmlk.notesmaker.ui.timelineslider.Orientation
import org.w3c.dom.Text
import java.time.LocalTime
import java.util.Collections.emptyList


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context= LocalContext.current
    val timeLineState= viewModel.timelinestate.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route+"?noteDate=${timeLineState.initialDate.toEpochDay()}")
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Keyboard.Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your note",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }


            var mainBackgroundColor by remember { mutableStateOf(DarkGray) }
            var selectedDateBackgroundColor by remember { mutableStateOf(Red) }
            var eventIndicatorColor by remember { mutableStateOf(Color.Black.copy(alpha = 0.35f)) }
            var dateTextColor by remember { mutableStateOf(Color.White) }

            DatePickerTimeline(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                onDateSelected = { viewModel.onEvent(NotesEvent.onDateSelected(it))},
                backgroundColor = mainBackgroundColor,
                state = timeLineState,
                orientation = Orientation.Horizontal,
                selectedBackgroundColor = selectedDateBackgroundColor,
                selectedTextColor = Color.White,
                dateTextColor = dateTextColor,
                eventDates = emptyList(),
                todayLabel = {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "To",
                        color = Color.White,
                        style = MaterialTheme.typography.h6
                    )
                },
                pastDaysCount = 1,
                eventIndicatorColor = eventIndicatorColor,
            )

            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->

                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}&noteDate=${note.timeStamp}"
                                )
                            },
                        onDeleteClick = {

                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }
        }
    }
}