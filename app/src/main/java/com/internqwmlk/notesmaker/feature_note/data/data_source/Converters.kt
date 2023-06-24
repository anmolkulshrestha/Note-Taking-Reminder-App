package com.internqwmlk.notesmaker.feature_note.data.data_source


import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

import androidx.room.TypeConverter

import com.internqwmlk.notesmaker.ui.theme.*
import kotlinx.coroutines.Dispatchers.Default

class Converters {
    @TypeConverter
    fun fromFontWeight(fontWeight: FontWeight):String?{
        if(fontWeight==FontWeight.Bold){
            return "FontWeight.Bold"
        }else{
             return "FontWeight.Normal"
        }
    }

    @TypeConverter
    fun toFontWeight(fontWeight:String): FontWeight?{
        if(fontWeight=="FontWeight.Bold"){ return FontWeight.Bold
        }
        else{return FontWeight.Normal
        }
    }


    @TypeConverter
    fun fromFontStyle(fontStyle: FontStyle):String?{
        if(fontStyle== FontStyle.Italic){
            return "FontStyle.Italic"

        }
        else{
            return "FontStyle.Normal"
        }
    }

    @TypeConverter
    fun toFontStyle(fontStyle: String):FontStyle?{
        if(fontStyle=="FontStyle.Italic"){
            return FontStyle.Italic

        }
        else{
            return FontStyle.Normal
        }
    }

    @TypeConverter
    fun fromTextDecoration(textDecoration: TextDecoration):String?{
        if(textDecoration==TextDecoration.Underline){
            return "TextDecoration.Underline"
        }else{
            return "TextDecoration.None"
        }
    }
    @TypeConverter
    fun toTextDecoration(textDecoration: String):TextDecoration?{
        if(textDecoration=="TextDecoration.Underline"){
            return TextDecoration.Underline
        }else{
            return TextDecoration.None
        }
    }

    @TypeConverter
    fun fromFontFamily(fontFamily: FontFamily):String?{
        return fontFamily.toString()
    }

    @TypeConverter
    fun toFontFamily(fontFamily:String):FontFamily?{
        return mapOfFontFamily[fontFamily]!!
    }



    fun Color.toHexCode(): String?{
        val red = this.red * 255
        val green = this.green * 255
        val blue = this.blue * 255
        return String.format("#%02x%02x%02x", red.toInt(), green.toInt(), blue.toInt())
    }

    val mapOfFontFamily = mapOf(
        (FontFamily.Default).toString() to FontFamily.Default,
        caveat_regular.toString() to caveat_regular,
        bungeeshade_regular.toString() to bungeeshade_regular,
        cabinsketch_regular.toString() to cabinsketch_regular,
        codystar_regular.toString() to codystar_regular,
        creepster_regular.toString() to creepster_regular,
        explora_regular.toString() to  explora_regular,
        monoton_regular.toString() to monoton_regular,
        overlock_regular.toString() to overlock_regular,
        rubikdirt_regular.toString() to rubikdirt_regular,
        rubikgemstones_regular.toString() to rubikgemstones_regular,
        rubikvinyl_regular.toString() to rubikvinyl_regular,
        shadowsintolight_regular.toString() to shadowsintolight_regular,
        turretroad_regular.toString() to turretroad_regular


    )
}
