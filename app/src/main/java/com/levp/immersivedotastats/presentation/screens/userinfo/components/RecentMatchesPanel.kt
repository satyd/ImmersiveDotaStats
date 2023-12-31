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
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.presentation.common.listitems.MatchEntryItem
import com.levp.immersivedotastats.presentation.common.PanelHeaderText
import com.levp.immersivedotastats.presentation.common.TinySpacer
import com.levp.immersivedotastats.presentation.theme.SmallPadding

@SuppressWarnings("FunctionNaming")
@Composable
fun RecentMatchesPanel(
    isLoading: Boolean = false,
    userRecentMatches: List<HistoryMatch>,
    onClick: (Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dp.SmallPadding)
    ) {
        PanelHeaderText(title = stringResource(id = R.string.recent_matches))
        TinySpacer()
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                userRecentMatches.forEachIndexed { index, historyMatch ->
                    MatchEntryItem(
                        historyMatch = historyMatch,
                        onClick = onClick
                    )
                    if (index != userRecentMatches.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}