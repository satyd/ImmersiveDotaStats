package com.levp.immersivedotastats.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.levp.immersivedotastats.R

@Composable
fun MedalView(rankTier: Byte) {
    val medalHeight = 60.dp
    Box(modifier = Modifier.wrapContentSize()) {
        if (rankTier % 10 != 0) {
            Icon(
                painter = painterResource(id = getStarsDrawableByTier(rankTier % 10)),
                contentDescription = null,
                modifier = Modifier.height(medalHeight),
                tint = Color.Unspecified
            )
        }
        Icon(
            painter = painterResource(id = getMedalDrawableByTier(rankTier / 10)),
            contentDescription = null,
            modifier = Modifier.size(medalHeight),
            tint = Color.Unspecified
        )
    }
}

fun getMedalDrawableByTier(tier: Int): Int {
    return when (tier) {
        1 -> R.drawable.medal_1
        2 -> R.drawable.medal_2
        3 -> R.drawable.medal_3
        4 -> R.drawable.medal_4
        5 -> R.drawable.medal_5
        6 -> R.drawable.medal_6
        7 -> R.drawable.medal_7
        8 -> R.drawable.medal_8
        else -> R.drawable.medal_0
    }
}

fun getStarsDrawableByTier(tier: Int): Int {
    return when (tier) {
        1 -> R.drawable.stars_1
        2 -> R.drawable.stars_2
        3 -> R.drawable.stars_3
        4 -> R.drawable.stars_4
        5 -> R.drawable.stars_5
        else -> R.drawable.stars_1
    }
}

@Composable
@Preview
fun MedalPreview(){
    Column() {
        MedalView(rankTier = 0)
        SmallSpacer()
        MedalView(rankTier = 33)
        SmallSpacer()
        MedalView(rankTier = 72)
        SmallSpacer()
        MedalView(rankTier = 80)
        SmallSpacer()
        MedalView(rankTier = 45)
        SmallSpacer()
        MedalView(rankTier = 63)
    }
}