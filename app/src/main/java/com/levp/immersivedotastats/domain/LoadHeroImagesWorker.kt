package com.levp.immersivedotastats.domain

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.levp.immersivedotastats.domain.use_case.LoadHeroImageUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoadHeroImagesWorker @Inject constructor(
    @ApplicationContext appContext: Context,
    private val loadHeroImageUseCase: LoadHeroImageUseCase,
    private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        Log.w("heheh","starting worker!")
        val heroImageUri =
            params.inputData.getString(KEY_IMAGES_URI) ?: return Result.failure()
        val heroId = params.inputData.getInt(KEY_HERO_IDS, -1) ?: return Result.failure()

        loadHeroImageUseCase.execute(heroImageUri, heroId.toString())

        return Result.success()
    }

    companion object {
        const val KEY_IMAGES_URI = "KEY_IMAGES_URI"
        const val KEY_HERO_IDS = "KEY_HERO_IDS"
    }
}