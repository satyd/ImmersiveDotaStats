package com.levp.immersivedotastats.presentation.common

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import java.io.File

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroImage(imageFile: File) {
    val image = BitmapFactory.decodeFile(imageFile.absolutePath)
    GlideImage(
        model = image,
        contentDescription = "",
        modifier = Modifier
            .height(48.dp)
            .padding(Dp.SmallPadding)
    )
}