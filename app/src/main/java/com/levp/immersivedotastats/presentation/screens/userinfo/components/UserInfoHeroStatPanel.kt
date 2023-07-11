package com.levp.immersivedotastats.presentation.screens.userinfo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.common.HeroStatEntryItem
import com.levp.immersivedotastats.presentation.common.MediumSpacer
import com.levp.immersivedotastats.presentation.common.SmallSpacer

@Composable
fun HeroStatPanel(
    isTurboEnabled: Boolean = true,
    userHeroesPerformance: List<HeroPerformanceStat>
) {
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = isTurboEnabled, onCheckedChange = {})
            MediumSpacer()
            Text(text = "Include turbo")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(
                userHeroesPerformance.subList(0, 6)
            ) { index: Int, item: HeroPerformanceStat ->
                HeroStatEntryItem(item)
            }
        }
    }

}