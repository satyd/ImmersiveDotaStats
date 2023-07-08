package com.levp.immersivedotastats.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat

@Composable
fun HeroStatEntry(heroStat: HeroPerformanceStat) {
    Row() {
        Icon(
            painter = painterResource(id = R.drawable.hero_icon_placeholder),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column() {
            Text(
                text = heroStat.heroName,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(text = heroStat.lastPlayed, fontSize = 12.sp, color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.size(4.dp))
        WinLossText(winCount = heroStat.wins, lossCount = heroStat.matches - heroStat.wins)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = heroStat.kDA.toString())
    }
}