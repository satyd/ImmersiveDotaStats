package com.levp.immersivedotastats.presentation.screens.userinfo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.data.remote.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.common.listitems.HeroStatEntryItem
import com.levp.immersivedotastats.presentation.common.PanelHeaderText
import com.levp.immersivedotastats.presentation.common.TinySpacer
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.utils.Constants

@SuppressWarnings("FunctionNaming")
@Composable
fun HeroStatPanel(
    isLoading: Boolean = false,
    userHeroesPerformance: List<HeroPerformanceStat>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dp.SmallPadding)
    ) {
        PanelHeaderText(title = stringResource(id = R.string.most_played))
        TinySpacer()
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                userHeroesPerformance.subList(0, Constants.HeroStatEntriesToShow)
                    .forEachIndexed { index, item ->
                        HeroStatEntryItem(item)
                        if (index != Constants.HeroStatEntriesToShow - 1) {
                            Divider()
                        }
                    }
            }
        }
    }
}