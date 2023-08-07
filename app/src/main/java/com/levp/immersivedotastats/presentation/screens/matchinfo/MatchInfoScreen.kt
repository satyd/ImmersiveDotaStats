package com.levp.immersivedotastats.presentation.screens.matchinfo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MatchInfoScreen(
    matchId: Long
) {
    Text(text = "Match Info $matchId")
}