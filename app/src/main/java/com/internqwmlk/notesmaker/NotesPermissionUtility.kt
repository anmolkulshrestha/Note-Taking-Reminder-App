package com.internqwmlk.notesmaker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

import androidx.activity.result.ActivityResultLauncher


import androidx.core.content.ContextCompat

object NotesPermissionUtility {
  fun requestOrUpdatePermissions(context: Context,permissionlauncher: ActivityResultLauncher<Array<String>>){


     var isScheduleExactAlarmPermissionGranted= ContextCompat.checkSelfPermission(context,
            Manifest.permission.SCHEDULE_EXACT_ALARM)== PackageManager.PERMISSION_GRANTED


        val permissionrequest:MutableList<String> = ArrayList()



        if(!isScheduleExactAlarmPermissionGranted) {
            permissionrequest.add(Manifest.permission.SCHEDULE_EXACT_ALARM)
        }


        if(permissionrequest.isNotEmpty()){
            permissionlauncher.launch(permissionrequest.toTypedArray())

        }else{


        }

    }


}