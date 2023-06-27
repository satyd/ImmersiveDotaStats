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
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.R

@Composable
fun UserInfoHeader(
    userInfoHeaderState: UserInfoHeaderState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .background(color = Color.LightGray)
    ) {
        Icon(
            painter = rememberAsyncImagePainter(userInfoHeaderState.profilePicLink),
            contentDescription = null,
            modifier = Modifier
                .size(width = 96.dp, height = 96.dp)
                .padding(4.dp)
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
                    text = userInfoHeaderState.profileName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                if (userInfoHeaderState.isDotaPlusSub) {
                    Image(
                        painter = painterResource(id = R.drawable.dotaplus_sub),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.rank_immortal),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    }
}
