package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.presentation.common.WinLossText

@Composable
fun UserInfoHeader(
    uiState: UserInfoState
) {
    val userInfo = uiState.userInfo
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .background(color = Color.LightGray)
    ) {
        Icon(
            painter = rememberAsyncImagePainter("${userInfo.userIcon}"),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                //.padding(4.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = userInfo.userName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                val loses = (userInfo.matches-userInfo.wins)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Matches: ${userInfo.matches}", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                WinLossText(winCount = userInfo.wins, lossCount = loses)
                Spacer(modifier = Modifier.height(4.dp))

            }
            Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                if (userInfo.isDotaPlusSub) {
                    Image(
                        painter = painterResource(id = R.drawable.dotaplus_sub),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.no_rank_medal),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    }
}
