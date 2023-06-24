package com.internqwmlk.notesmaker.feature_alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.internqwmlk.notesmaker.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DeviceRestartBroadcastReciver :BroadcastReceiver() {

@Inject lateinit var noteUseCases: NoteUseCases
@Inject lateinit var alarmScheduler: AlarmScheduler
    override fun onReceive(p0: Context?, p1: Intent?) {

        if (p1?.action == "android.intent.action.BOOT_COMPLETED") {
         GlobalScope.launch {
       noteUseCases.getAlarms().onEach {
        it.forEach {
        alarmScheduler.addAlarm(it)
    }
}

         }

        }
    }
}