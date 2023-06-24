package com.internqwmlk.notesmaker.feature_note.domain.model





import android.graphics.fonts.FontFamily
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.internqwmlk.notesmaker.ui.theme.*


@Entity(tableName = "note")
data class Note(
    var title:String,
    var content:String,
    var timeStamp:Long,
    var alarmId:Long?=null,
    var color: Int,
    var titleColor:Int,
    var contentColor:Int,
    var titleSize:Int,
    var contentSize:Int,
    var titleFontStyle:String,
    var contentFontStyle:String,
    var titleFontWeight:FontWeight,
    var contentFontWeight:FontWeight,
    var titleTextDecoration:TextDecoration,
    var contentTextDecoration:TextDecoration,
    var titleFontFamily: FontFamily,
    var contentFontFamily:FontFamily,





    @PrimaryKey var id:Int?=null
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

}