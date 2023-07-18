package com.levp.immersivedotastats.presentation.common.itemparts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.presentation.theme.StatsTheme

@Composable
fun WinLossText(winCount: Int, lossCount: Int) {
    val total = winCount + lossCount
    val textBoxSize = remember { mutableStateOf(Size.Zero) }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
        //.widthIn(30.dp, 100.dp)
    ) {
        WinLossTextBox(
            win = winCount,
            lose = lossCount,
            onSizeChanged = { size -> textBoxSize.value = size }
        )
        var wrStripeWidth = 60.dp
        if (total != 0) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(wrStripeWidth),
                horizontalArrangement = Arrangement.Center
            ) {
                val winPercent = winCount.toFloat() / total
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .width(wrStripeWidth * winPercent)
                        //.fillMaxWidth(fraction = winPercent)
                        .background(StatsTheme.colors.winStat)
                )
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .width(wrStripeWidth * (1 - winPercent))
                        //.fillMaxWidth(fraction = 1f - winPercent)
                        .background(StatsTheme.colors.loseStat)
                )
                //Log.d("hehe", "wr = ${winPercent}, 1-wr = ${1 - winPercent}")
            }
        }
    }
}

@Composable
fun WinLossTextBox(win: Int, lose: Int, onSizeChanged: (Size) -> Unit) {
    val size = remember { mutableStateOf(Size.Zero) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .onGloballyPositioned { coordinates ->
                size.value =
                    Size(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())
                onSizeChanged(size.value)
                //Log.d("hehe", "size changed to ${size.value}")
            }
    ) {
        Row() {
            WinText(count = win)
            Text(text = " - ", fontSize = 12.sp)
            LossText(count = lose)
        }
    }
}

@Composable
fun WinText(count: Int) {
    Text(
        text = count.toString(),
        fontSize = 12.sp,
        color = StatsTheme.colors.winStat
    )
}

@Composable
fun LossText(count: Int) {
    Text(
        text = count.toString(),
        fontSize = 12.sp,
        color = StatsTheme.colors.loseStat
    )
}

@Composable
@Preview
fun WinLossTextPreview(){
    WinLossText(winCount = 123, lossCount = 128)
}