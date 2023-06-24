package com.internqwmlk.notesmaker.feature_notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.R
import androidx.core.app.NotificationCompat
import com.internqwmlk.notesmaker.R
import com.internqwmlk.notesmaker.feature_note.presentation.MainActivity


class ReminderNotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(message:String){
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification=NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setContentTitle("Reminder")
            .setContentText("$message")
            .setSmallIcon(R.drawable.ic_baseline_group_work_24)
            .setContentIntent(activityPendingIntent)
            .setSound(alarmSound)
            .build()


        notificationManager.notify(1,notification)
    }

    companion object{
        const val REMINDER_CHANNEL_ID="reminder_channel_id"
    }
}