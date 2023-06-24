package com.internqwmlk.notesmaker.feature_note.presentation.edit_add_note.components

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internqwmlk.notesmaker.ui.AutoResizedText
import com.internqwmlk.notesmaker.ui.theme.*

@Composable
fun fontFamilySelectionItem(fontFamilySelectionItemTile: fontFamilySelectionItemTile){

    BoxWithConstraints(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(2f)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(46,53,71,255))

            .padding(4.dp)
    ) {
      //  AutoResizedText(text = "EasyNotes",fontFamilySelectionItemTile)
    }



}

data class fontFamilySelectionItemTile(
                                 val fontFamily: FontFamily = caveat_regular)

val listOfFontFamily:MutableList<fontFamilySelectionItemTile> = mutableListOf(
   fontFamilySelectionItemTile(fontFamily = bungeeshade_regular),
    fontFamilySelectionItemTile(fontFamily = cabinsketch_regular),
    fontFamilySelectionItemTile(fontFamily = caveat_regular),
    fontFamilySelectionItemTile(fontFamily = codystar_regular),
    fontFamilySelectionItemTile(fontFamily = creepster_regular),
    fontFamilySelectionItemTile(fontFamily = explora_regular),
    fontFamilySelectionItemTile(fontFamily = monoton_regular),
    fontFamilySelectionItemTile(fontFamily = overlock_regular),
    fontFamilySelectionItemTile(fontFamily = rubikdirt_regular),
    fontFamilySelectionItemTile(fontFamily = rubikgemstones_regular),
    fontFamilySelectionItemTile(fontFamily = rubikvinyl_regular),
    fontFamilySelectionItemTile(fontFamily = shadowsintolight_regular),
    fontFamilySelectionItemTile(fontFamily = turretroad_regular),





)