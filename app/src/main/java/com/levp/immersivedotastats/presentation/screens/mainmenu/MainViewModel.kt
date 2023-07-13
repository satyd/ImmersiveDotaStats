package com.levp.immersivedotastats.presentation.screens.mainmenu

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.levp.immersivedotastats.domain.LoadHeroImagesWorker
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import com.levp.immersivedotastats.domain.usecases.LoadImageUseCase
import com.levp.immersivedotastats.presentation.screens.heroesinfo.HeroInfoViewEntity
import com.levp.immersivedotastats.utils.HeroInfoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HeroInfoRepository,
    private val loadImageUseCase: LoadImageUseCase
) : ViewModel() {

    private lateinit var workManager: WorkManager

    init {
        Log.i("hehe", "MainVM: init")
        loadHeroes()
    }

    val heroInfoList: MutableStateFlow<List<HeroInfoViewEntity>> = MutableStateFlow(emptyList())

    fun loadHeroes() {
        val mapper = HeroInfoMapper()
        viewModelScope.launch {
            val response = try {
                Log.i("hehe", "MainVM: requesting heroes response...")
                RetrofitInstance.heroApi.getHeroStatsInfo()
            } catch (e: IOException) {
                Log.e("hehe", "MainVM: IO Exception")
                return@launch
            } catch (e: HttpException) {
                Log.e("hehe", "MainVM: No Internet!")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                Log.i("hehe", "MainVM: got response")
                val list = response.body()!!
                heroInfoList.emit(mapper.mapList(list))
            } else {
                Log.w("hehe", "MainVM: Loading heroes failed! Code: ${response.code()}")
            }
        }
    }

    fun loadHeroImages(context: Context) {
        workManager = WorkManager.getInstance(context)

        viewModelScope.launch {
            Log.i("hehe", "MainVM: requesting worker to run ${heroInfoList.value.size} requests")
            for (hero in heroInfoList.value) {
                loadImageUseCase.execute(hero.image, hero.id.toString())
            }
        }
    }

    fun tryingWorkerOld() {
        for (hero in heroInfoList.value) {
            val request = OneTimeWorkRequestBuilder<LoadHeroImagesWorker>().setInputData(
                workDataOf(
                    LoadHeroImagesWorker.KEY_IMAGES_URI to hero.image,
                    LoadHeroImagesWorker.KEY_HERO_IDS to hero.id
                )
            ).setConstraints(
                Constraints(
                    //requiresStorageNotLow = true,
                    //requiredNetworkType = NetworkType.CONNECTED
                )
            ).build()
            workManager.enqueue(request)
        }
    }
}
