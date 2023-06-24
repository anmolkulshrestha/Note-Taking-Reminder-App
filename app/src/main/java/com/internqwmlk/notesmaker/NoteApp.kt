package com.internqwmlk.notesmaker

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import com.internqwmlk.notesmaker.feature_notification.ReminderNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp: Application(){

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
           val channel=  NotificationChannel(
               ReminderNotificationService.REMINDER_CHANNEL_ID,
               "Reminder",
               NotificationManager.IMPORTANCE_HIGH
           )
            channel.description="used for reminding tasks"
            val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}