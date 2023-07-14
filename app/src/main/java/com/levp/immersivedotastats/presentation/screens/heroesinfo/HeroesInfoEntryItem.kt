package com.levp.immersivedotastats.presentation.screens.heroesinfo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.domain.network.Path
import com.levp.immersivedotastats.presentation.common.HeroImage
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import java.io.File

@Composable
fun HeroesInfoEntryItem(viewEntity: HeroInfoViewEntity) {
    val baseImgUrl = Path.BASE_URL
    val imageFile = File(LocalContext.current.cacheDir, "heroImg_${viewEntity.id}.jpg")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageFile.exists()) {
            HeroImage(imageFile = imageFile)
        } else {
            Image(
                painter = rememberAsyncImagePainter("$baseImgUrl${viewEntity.image}"),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
        ) {
            Row {
                SmallSpacer()
                Text(
                    text = viewEntity.locName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                SmallSpacer()
                //Text(text = "has ${viewEntity.legs} legs", fontSize = 24.sp)
            }
            Text(
                text = viewEntity.roles.shuffled().toString().replace(Regex("[\\[\\]]"), ""),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                maxLines = 2
            )
        }
    }
}