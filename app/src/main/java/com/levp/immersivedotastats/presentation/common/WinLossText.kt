package com.levp.immersivedotastats.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun WinLossText(winCount: Int, lossCount: Int) {
    Row() {
        WinText(count = winCount)
        Text(text = " - ", fontSize = 12.sp)
        LossText(count = lossCount)
    }
}

@Composable
fun WinText(count: Int) {
    Text(
        text = count.toString(),
        fontSize = 12.sp,
        color = Color.Green
    )
}

@Composable
fun LossText(count: Int) {
    Text(
        text = count.toString(),
        fontSize = 12.sp,
        color = Color.Red
    )
}