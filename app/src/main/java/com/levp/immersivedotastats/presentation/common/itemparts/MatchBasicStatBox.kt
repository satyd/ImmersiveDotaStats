package com.levp.immersivedotastats.presentation.common.itemparts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.presentation.common.MedalView
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import com.levp.immersivedotastats.presentation.theme.TinyPadding
import com.levp.immersivedotastats.utils.formatDuration

@Composable
fun MatchBasicStatBox(
    historyMatch: HistoryMatch
) {
    with(historyMatch) {
        val matchResult = if (isVictory) "Win" else "Loss"
        val duration = formatDuration(durationSeconds)
        Box(
            modifier = Modifier
                .widthIn(min = 150.dp, max = 230.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = matchResult,
                color = if (isVictory) StatsTheme.colors.winStat else StatsTheme.colors.loseStat,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(Dp.TinyPadding)
            )
            Text(
                text = duration,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(Dp.TinyPadding)
            )
            Text(
                text = "$kills/$deaths/$assists",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(Dp.SmallPadding)
            )
            MedalView(
                rankTier = rank.toByte(),
                medalHeight = 32.dp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
    }
}