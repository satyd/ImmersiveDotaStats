package com.levp.immersivedotastats.presentation.screens.userinfo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.common.HeroStatEntryItem
import com.levp.immersivedotastats.presentation.common.MediumSpacer
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.utils.Constants

@SuppressWarnings("FunctionNaming")
@Composable
fun HeroStatPanel(
    isLoading: Boolean = false,
    isTurboEnabled: Boolean = true,
    userHeroesPerformance: List<HeroPerformanceStat>,
    isTurboEnabledSwitch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dp.SmallPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = isTurboEnabled, onCheckedChange = { isTurboEnabledSwitch() })
            MediumSpacer()
            Text(text = "Include turbo")
        }
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(
                    userHeroesPerformance.subList(0, Constants.HeroStatEntriesToShow)
                ) { index: Int, item: HeroPerformanceStat ->
                    HeroStatEntryItem(item)
                    if (index != Constants.HeroStatEntriesToShow - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}