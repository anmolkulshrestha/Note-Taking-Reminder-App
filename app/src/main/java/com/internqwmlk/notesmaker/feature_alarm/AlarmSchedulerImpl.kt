package com.internqwmlk.notesmaker.feature_alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote


class AlarmSchedulerImpl(
    private val context: Context
):AlarmScheduler {
    private val alarmManager=context.getSystemService(AlarmManager::class.java)

    override fun addAlarm(noteAlarmNote: AlarmNote) {
         val intent= Intent(context,AlarmReciver::class.java,).apply {
             putExtra("EXTRA_MESSAGE",noteAlarmNote.message)
         }

         alarmManager.setExactAndAllowWhileIdle(
             AlarmManager.RTC_WAKEUP,
         noteAlarmNote.time,
         PendingIntent.getBroadcast(context,
             noteAlarmNote.alarmId!!.toInt(),intent,
         PendingIntent.FLAG_UPDATE_CURRENT or
                 PendingIntent.FLAG_IMMUTABLE))

    }

    override fun cancleAlarm(noteAlarmNote: AlarmNote) {
           alarmManager.cancel( PendingIntent.getBroadcast(context,
               noteAlarmNote.alarmId!!.toInt(),Intent(context,AlarmReciver::class.java),
               PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE))

    }

}