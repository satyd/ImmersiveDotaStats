package com.levp.immersivedotastats.domain.usecases

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.levp.immersivedotastats.domain.FileTarget
import com.levp.immersivedotastats.domain.network.Path
import com.levp.immersivedotastats.utils.Constants
import java.io.File


class LoadImageUseCase(
    val appContext: Context
) {
    fun execute(imageUrl: String, heroId: String) {
        val targetFile: File = File(appContext.cacheDir, "heroImg_${heroId}.jpg")
        Log.i("heheh","trying to load ${imageUrl}, $heroId")
        Glide.with(appContext)
            .load("${Path.BASE_URL}$imageUrl")
            .error(Constants.CatNotFoundUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(FileTarget(targetFile))

        //Log.i("heheh","creating file ${targetFile.absolutePath}")
    }
}