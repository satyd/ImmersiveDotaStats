package com.levp.immersivedotastats.domain.use_case

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.levp.immersivedotastats.domain.FileTarget
import com.levp.immersivedotastats.data.repository.Path
import com.levp.immersivedotastats.utils.Constants
import com.levp.immersivedotastats.utils.formatHeroImageFile
import java.io.File
import javax.inject.Inject


class LoadImageUseCase @Inject constructor(
    private val appContext: Context
) {
    fun execute(imageUrl: String, heroId: String) {
        val targetFile =
            File(appContext.cacheDir, formatHeroImageFile(heroId))
        Glide.with(appContext)
            .load("${Path.BASE_URL}$imageUrl")
            .error(Constants.CatNotFoundUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(FileTarget(targetFile))
    }
}