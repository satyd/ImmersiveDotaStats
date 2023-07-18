package com.levp.immersivedotastats.presentation.common.listitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.presentation.common.itemparts.HeroImage
import com.levp.immersivedotastats.presentation.common.itemparts.MatchBasicStatBox
import com.levp.immersivedotastats.presentation.common.TinySpacer
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import java.io.File

@Composable
fun MatchEntryItem(historyMatch: HistoryMatch) {
    val imageFile = File(LocalContext.current.cacheDir, "heroImg_${historyMatch.heroId}.jpg")
    with(historyMatch) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.wrapContentSize()) {
                HeroImage(imageFile = imageFile)
                TinySpacer()
                Column {
                    Text(
                        text = heroName,
                        color = StatsTheme.colors.subText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    TinySpacer()
                }
            }
            MatchBasicStatBox(historyMatch = historyMatch)
        }
    }
}
