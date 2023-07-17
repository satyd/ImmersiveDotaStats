package com.levp.immersivedotastats.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import com.levp.immersivedotastats.utils.formatDuration
import java.io.File
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun MatchEntryItem(historyMatch: HistoryMatch) {
    val imageFile = File(LocalContext.current.cacheDir, "heroImg_${historyMatch.heroId}.jpg")
    val matchResult = if (historyMatch.isVictory) "Win" else "Loss"
    val duration = formatDuration(historyMatch.durationSeconds)

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.wrapContentSize()) {
            HeroImage(imageFile = imageFile)
            TinySpacer()
            Column {
                Text(text = historyMatch.heroName, color = StatsTheme.colors.subText)
                TinySpacer()
                Text(text = duration, color = Color.LightGray)
            }
        }
        Row(modifier = Modifier.wrapContentSize()) {
            Text(
                text = matchResult,
                color = if (historyMatch.isVictory) StatsTheme.colors.winStat else StatsTheme.colors.loseStat
            )
            SmallSpacer()
            KDAView(k = historyMatch.kills, d = historyMatch.deaths, a = historyMatch.assists)
        }
        SmallSpacer()
        MedalView(rankTier = historyMatch.rank.toByte(), medalHeight = 32.dp)
    }
}
