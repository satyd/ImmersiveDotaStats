package com.levp.immersivedotastats.presentation.common

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.utils.formatDateTime
import java.time.Instant
import java.time.format.DateTimeFormatter

@Composable
fun HeroStatEntryItem(heroStat: HeroPerformanceStat) {
    val dateTime = formatDateTime(heroStat.lastPlayed.toLong())
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.hero_icon_placeholder),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            SmallSpacer()
            Column() {
                Text(
                    text = heroStat.heroName,
                    fontSize = 14.sp,
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

