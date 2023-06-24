package com.internqwmlk.notesmaker.feature_alarm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_table")
data class AlarmNote(
    val time:Long,
    val message:String,
    @PrimaryKey val alarmId:Long?=null


    )