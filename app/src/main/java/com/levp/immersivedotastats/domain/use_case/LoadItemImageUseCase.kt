package com.levp.immersivedotastats.domain.use_case

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.levp.immersivedotastats.domain.FileTarget
import com.levp.immersivedotastats.data.repository.Path
import com.levp.immersivedotastats.utils.Constants
import java.io.File
import javax.inject.Inject


class LoadItemImageUseCase(
    private val appContext: Context
) {
    fun execute(imageUrl: String, itemId: String) {
        val targetFile: File = File(appContext.cacheDir, "itemImg_${itemId}.jpg")
        Log.i("heheh","trying to load ${imageUrl}, $itemId")
        Glide.with(appContext)
            .load("${Path.BASE_URL}$imageUrl")
            .error(Constants.CatNotFoundUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(FileTarget(targetFile))
    }
}