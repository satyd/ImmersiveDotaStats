package com.levp.immersivedotastats.presentation.screens.userinfo.components

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.presentation.common.MainSpacer
import com.levp.immersivedotastats.presentation.common.MedalView
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import com.levp.immersivedotastats.presentation.common.TinySpacer
import com.levp.immersivedotastats.presentation.common.WinLossText
import com.levp.immersivedotastats.presentation.screens.userinfo.UserInfoState
import com.levp.immersivedotastats.presentation.theme.LargePadding
import com.levp.immersivedotastats.presentation.theme.MediumPadding
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.presentation.theme.StatsTheme

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalTextApi::class)
@SuppressWarnings("FunctionNaming")
@Composable
fun UserInfoHeader(
    uiState: UserInfoState
) {
    val userInfo = uiState.userInfo
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(color = StatsTheme.colors.headerBackground),
        verticalAlignment = Alignment.Top
    ) {
        GlideImage(
            model = userInfo.userIcon,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .padding(Dp.SmallPadding)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dp.SmallPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = userInfo.userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = StatsTheme.colors.mainText
                    )
                    SmallSpacer()
                    if (uiState.userInfo.isDotaPlusSub) {
                        Text(
                            text = "+",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Yellow
                        )
                    }
                }
                val loses = (userInfo.matches - userInfo.wins)
                TinySpacer()
                Text(text = "Matches: ${userInfo.matches}", fontSize = 12.sp)
                TinySpacer()
                WinLossText(winCount = userInfo.wins, lossCount = loses)
            }
            Row(
                modifier = Modifier.wrapContentHeight().padding(Dp.SmallPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MedalView(rankTier = userInfo.seasonRank)
            }
        }
    }
}


@SuppressWarnings("FunctionNaming")
@Composable
@Preview
fun UserInfoHeaderPreview() {
    UserInfoHeader(uiState = UserInfoState.getEmpty())
}