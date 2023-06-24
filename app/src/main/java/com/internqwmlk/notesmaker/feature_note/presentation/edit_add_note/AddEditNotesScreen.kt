package com.internqwmlk.notesmaker.feature_note.presentation.notes





import android.app.AlarmManager
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.Icon
import android.inputmethodservice.Keyboard

import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Button

import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.AddEditNoteEvent
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.AddEditNoteViewModel






import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*



import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm

import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.internqwmlk.notesmaker.feature_alarm.AlarmSchedulerImpl
import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote
import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.NoteTextFieldState
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TextStylingPallete
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TextStylingPalleteState
import com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components.TransparentHintTextField
import com.internqwmlk.notesmaker.feature_note.presentation.notes.NotesEvent
import com.internqwmlk.notesmaker.ui.theme.Purple500
import com.internqwmlk.notesmaker.ui.timelineslider.DatePickerTimeline
import com.internqwmlk.notesmaker.ui.timelineslider.Orientation
import com.internqwmlk.notesmaker.ui.timelineslider.sliderstate.rememberDatePickerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    noteDate:Long,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val context= LocalContext.current
    val titleState = viewModel.noteTitle.value
    val textStylingPalleteState:TextStylingPalleteState=viewModel.textStylingPalleteState.value
    val contentState = viewModel.noteContent.value


  //  val scaffoldState = rememberScaffoldState()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    BottomSheetScaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    viewModel.onEvent(AddEditNoteEvent.SaveNote(noteDate))
                },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 100.dp),

            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        sheetPeekHeight = 0.dp,

        sheetContent = {
            TextStylingPallete(Modifier, textStylingPalleteState)
        },
        sheetBackgroundColor = Color(26, 32, 46, 255),
        sheetShape = RoundedCornerShape(15.dp),

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Keyboard.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.clip(RectangleShape),onClick = {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    val alarmManager = ContextCompat.getSystemService(context, AlarmManager::class.java)
                    if (alarmManager?.canScheduleExactAlarms() == false) {
                        Intent().also { intent ->
                            intent.action = Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                            context.startActivity(intent)
                        }
                    }
                }else{
                    val timePickerDialog = TimePickerDialog(
                        context,
                        {_, hour : Int, minute: Int ->
                            viewModel.onEvent(AddEditNoteEvent.setAlarm(LocalTime.of(hour, minute)))


                        },LocalTime.now().hour, LocalTime.now().minute, false
                    ).show()



                }

            }) {
                Icon(Icons.Default.Alarm,"set alarm")
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Set Alarm")
            }
//            ShowTimePicker(context =context, initHour = LocalTime.now().hour, initMinute = LocalTime.now().minute,{
//                viewModel.onEvent(AddEditNoteEvent.setAlarm(it))
//            })


            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                fontFamily = titleState.fontFamily,
                onTextStylingClick = {
                    viewModel.onEvent(AddEditNoteEvent.changeTextStylePalleteSatate(
                        TextStylingPalleteState(
                            titleState,
                            {color->
                                viewModel.onEvent(AddEditNoteEvent.changeTitleColor(color = color))

                            },
                            {fontStyle->
                                viewModel.onEvent(AddEditNoteEvent.changeTitleFontStyle(fontStyle))

                            },
                            {fontWeight->
                                viewModel.onEvent(AddEditNoteEvent.changeTitleFontWeight(fontWeight))

                            },
                            {textDecoration->
                                viewModel.onEvent(AddEditNoteEvent.changeTitleTextDecoration(textDecoration))

                            },
                            {fontFamily->
                                viewModel.onEvent(AddEditNoteEvent.changeTitleFontFamily(fontFamily))

                            },
                            {size->
                                viewModel.onEvent(AddEditNoteEvent.incrementTitleTextSize(size))
                            },
                            {size->
                                viewModel.onEvent(AddEditNoteEvent.decremnetTitleTextSize(size))
                            },


                        )
                    )

                    )

                    scope.launch {
                        if(sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }}
                ,
                textSize=titleState.textSize,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                textColor = titleState.color
            )
            Spacer(modifier = Modifier.height(16.dp))

                TransparentHintTextField(

                    text = contentState.text,
                    fontFamily = contentState.fontFamily,
                    textSize=contentState.textSize,
                    fontWeight = contentState.fontWeight,
                    fontStyle = contentState.fontstyle,
                    textDecoration = contentState.textDecoration,
                    hint = contentState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,

                    textColor = contentState.color,
                    modifier = Modifier.fillMaxHeight(),
                            onTextStylingClick = {
                        viewModel.onEvent(AddEditNoteEvent.changeTextStylePalleteSatate(
                            TextStylingPalleteState(
                                contentState,
                                {color->
                                    viewModel.onEvent(AddEditNoteEvent.changeContentColor(color = color))

                                },
                                {fontStyle->
                                    viewModel.onEvent(AddEditNoteEvent.changeContentFontStyle(fontStyle))

                                },
                                {fontWeight->
                                    viewModel.onEvent(AddEditNoteEvent.changeContentFontWeight(fontWeight))

                                },
                                {textDecoration->
                                    viewModel.onEvent(AddEditNoteEvent.changeContentTextDecoration(textDecoration))

                                },
                                {fontFamily->
                                    viewModel.onEvent(AddEditNoteEvent.changeContentFontFamily(fontFamily))

                                },
                                {size->
                                    viewModel.onEvent(AddEditNoteEvent.incrementContentTextSize(size))
                                },
                                {size->
                                    viewModel.onEvent(AddEditNoteEvent.decrementContentTextSize(size))
                                },



                            )
                         )

                        )
                        scope.launch {
                            if(sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }
                        }

                    },
                )



            }

                }
    }



@Composable
fun ShowTimePicker(context: Context, initHour: Int, initMinute: Int,onTimeSelect:(LocalTime)->Unit) {
    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            onTimeSelect(LocalTime.of(hour, minute))

        }, initHour, initMinute, false
    )

    Button(modifier = Modifier.clip(RectangleShape),onClick = {

            timePickerDialog.show()


    }) {
        Icon(Icons.Default.Alarm,"set alarm")
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Set Alarm")
    }
}