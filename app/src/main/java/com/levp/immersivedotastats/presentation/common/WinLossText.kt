package com.levp.immersivedotastats.presentation.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WinLossText(winCount: Int, lossCount: Int) {
    val total = winCount + lossCount
    //val winPercent = remember{ mutableStateOf(0f) }
    val textBoxSize = remember { mutableStateOf(Size.Zero) }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .widthIn(30.dp, 80.dp)
    ) {
        WinLossTextBox(
            win = winCount,
            lose = lossCount,
            onSizeChanged = { size -> textBoxSize.value = size }
        )
        if (total != 0) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(textBoxSize.value.width.dp)
            ) {
                val winPercent = winCount.toFloat() / total
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        //.width(30.dp)
                        .fillMaxWidth(fraction = winPercent)
                        .background(Color.Green)
                )
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        //.width(145.dp)
                        .fillMaxWidth(fraction = 1f - winPercent)
                        .background(Color.Red)
                )
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
                Log.d("hehe","size changed to ${size.value}")
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