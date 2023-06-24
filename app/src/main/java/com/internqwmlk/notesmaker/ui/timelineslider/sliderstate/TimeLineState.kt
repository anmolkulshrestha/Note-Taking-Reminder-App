package com.internqwmlk.notesmaker.ui.timelineslider.sliderstate

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import java.time.LocalDate

@Stable
interface TimeLineState {
   val initialDate:LocalDate
   val shouldScrollToSelectedDate:Boolean
   fun scrollToSelectedDate(date:LocalDate)
   fun onScrollCompleted()
}

class TimeLineStateImpl(
    selectedDate: LocalDate,
    shouldScrollToSelectedDate: Boolean = true
):TimeLineState{

    private var _initialDate by mutableStateOf(selectedDate, structuralEqualityPolicy())
    private var _shouldScrollToSelectedDate by mutableStateOf(
        shouldScrollToSelectedDate,
        structuralEqualityPolicy()
    )

    override fun onScrollCompleted() {
        _shouldScrollToSelectedDate = false
    }

    override val shouldScrollToSelectedDate: Boolean
        get() = _shouldScrollToSelectedDate

    override val initialDate: LocalDate
        get() = _initialDate

    override fun scrollToSelectedDate(date: LocalDate) {
        _shouldScrollToSelectedDate = true
        _initialDate = date
    }
    companion object {
        val Saver: Saver<TimeLineState, *> = listSaver(
            save = {
                listOf(
                    it.initialDate.year,
                    it.initialDate.monthValue,
                    it.initialDate.dayOfMonth,
                    it.shouldScrollToSelectedDate.toString()
                )
            },
            restore = {
              TimeLineStateImpl(
                    selectedDate = LocalDate.of(
                        it[0].toString().toInt(), // year
                        it[1].toString().toInt(), // month
                        it[2].toString().toInt(), // day
                    ),

                    shouldScrollToSelectedDate = it[3].toString()
                        .toBoolean() // shouldScrollToSelectedDate
                )
            }
        )
    }



    }
@Composable
fun rememberDatePickerState(initialDate: LocalDate = LocalDate.now()) =
    rememberSaveable(saver = TimeLineStateImpl.Saver) { TimeLineStateImpl(initialDate) }