package com.levp.immersivedotastats.presentation.common

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.utils.formatDateTime
import java.io.File
import java.time.Instant
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroStatEntryItem(heroStat: HeroPerformanceStat) {
    val dateTime = formatDateTime(heroStat.lastPlayed.toLong())

    val imageFile = File(LocalContext.current.cacheDir, "heroImg_${heroStat.heroId}.jpg")

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row {
            if (imageFile.exists()) {
                HeroImage(imageFile = imageFile)
                /*Icon(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )*/
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.hero_icon_placeholder),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
            SmallSpacer()
            Column() {
                Text(
                    text = heroStat.heroName,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = dateTime, fontSize = 12.sp, color = Color.DarkGray)
            }
        }
        Row {
            WinLossText(winCount = heroStat.wins, lossCount = heroStat.matches - heroStat.wins)
            SmallSpacer()
            Text(text = heroStat.kDA.toString())
            SmallSpacer()
        }
    }
}

