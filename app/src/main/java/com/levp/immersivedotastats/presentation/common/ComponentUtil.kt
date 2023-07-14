package com.levp.immersivedotastats.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.levp.immersivedotastats.presentation.theme.DozenPadding
import com.levp.immersivedotastats.presentation.theme.MediumPadding
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.presentation.theme.TinyPadding

@Composable
fun TinySpacer() {
    Spacer(modifier = Modifier.size(Dp.TinyPadding))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(Dp.SmallPadding))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.size(Dp.MediumPadding))
}

@Composable
fun MainSpacer() {
    Spacer(modifier = Modifier.size(Dp.DozenPadding))
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun BrushText(symbol: String) {
    val gradientColors = listOf(Color.Yellow, Color.Red)
    Text(
        text = symbol,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        )
    )
}