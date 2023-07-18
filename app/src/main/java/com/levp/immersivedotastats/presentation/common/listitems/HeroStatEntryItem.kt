package com.levp.immersivedotastats.presentation.common.listitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.data.remote.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.common.itemparts.HeroImage
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import com.levp.immersivedotastats.presentation.common.TinySpacer
import com.levp.immersivedotastats.presentation.common.itemparts.WinLossText
import com.levp.immersivedotastats.utils.formatDateTime
import java.io.File

@Composable
fun HeroStatEntryItem(heroStat: HeroPerformanceStat) {
    val dateTime = formatDateTime(heroStat.lastPlayed.toLong())

    val imageFile = File(LocalContext.current.cacheDir, "heroImg_${heroStat.heroId}.jpg")

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row {
            HeroImage(imageFile = imageFile)
            TinySpacer()
            Column() {
                Text(
                    text = heroStat.heroName,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = dateTime, fontSize = 12.sp, color = Color.DarkGray)
            }
        }
        Row {
            WinLossText(winCount = heroStat.wins, lossCount = heroStat.matches - heroStat.wins)
            SmallSpacer()
            Text(text = heroStat.kDA.toString())
            SmallSpacer()
        }
    }
}

