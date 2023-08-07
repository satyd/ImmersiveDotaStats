package com.levp.immersivedotastats.presentation.screens.matchinfo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun MatchInfoScreen(
    matchId: Long,
    viewModel: MatchInfoViewModel = singleViewModel()
) {
    viewModel.setMatchId(matchId)
    Text(text = "Match Info $matchId")
}