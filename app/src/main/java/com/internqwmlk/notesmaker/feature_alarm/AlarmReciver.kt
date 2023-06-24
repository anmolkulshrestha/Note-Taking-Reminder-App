package com.internqwmlk.notesmaker.feature_alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.internqwmlk.notesmaker.feature_notification.ReminderNotificationService

class AlarmReciver:BroadcastReceiver (){
    override fun onReceive(p0: Context?, p1: Intent?) {
         val message=p1?.getStringExtra("EXTRA_MESSAGE")?:return
         val notificationservice=ReminderNotificationService(p0!!)
         notificationservice.showNotification(message)
    }
}