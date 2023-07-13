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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import com.levp.immersivedotastats.presentation.common.WinLossText
import com.levp.immersivedotastats.presentation.screens.userinfo.UserInfoState
import com.levp.immersivedotastats.presentation.theme.LargePadding
import com.levp.immersivedotastats.presentation.theme.MediumPadding
import com.levp.immersivedotastats.presentation.theme.SmallPadding

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressWarnings("FunctionNaming")
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
        GlideImage(
            model = userInfo.userIcon,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .padding(Dp.SmallPadding)
        )
        /*Icon(
            painter = rememberAsyncImagePainter(userInfo.userIcon),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(Dp.SmallPadding)
        )*/
        Spacer(modifier = Modifier.width(8.dp))
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(top = Dp.LargePadding)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Text(
                        text = userInfo.userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    SmallSpacer()
                    Icon(
                        painter = painterResource(id = R.drawable.dotaplus_small_icon),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
                val loses = (userInfo.matches - userInfo.wins)
                SmallSpacer()
                Text(text = "Matches: ${userInfo.matches}", fontSize = 12.sp)
                SmallSpacer()
                WinLossText(winCount = userInfo.wins, lossCount = loses)
                SmallSpacer()

            }
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_rank_medal),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
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