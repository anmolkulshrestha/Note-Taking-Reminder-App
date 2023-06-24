package com.internqwmlk.notesmaker.feature_alarm

import com.internqwmlk.notesmaker.feature_alarm.domain.model.AlarmNote

interface AlarmScheduler {
    fun addAlarm(noteAlarmNote: AlarmNote)
    fun cancleAlarm(noteAlarmNote: AlarmNote)
}