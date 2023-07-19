package com.levp.immersivedotastats.presentation.screens.mainmenu

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.levp.immersivedotastats.domain.LoadHeroImagesWorker
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.data.repository.RetrofitInstance
import com.levp.immersivedotastats.domain.use_case.LoadHeroImageUseCase
import com.levp.immersivedotastats.data.remote.dto.heroinfo.HeroInfoViewEntity
import com.levp.immersivedotastats.data.HeroInfoMapper
import com.levp.immersivedotastats.data.remote.dto.item.Item
import com.levp.immersivedotastats.data.remote.dto.item.ItemWrap
import com.levp.immersivedotastats.domain.use_case.LoadItemImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HeroInfoRepository,
    private val loadHeroImageUseCase: LoadHeroImageUseCase,
    private val loadItemImageUseCase: LoadItemImageUseCase
) : ViewModel() {

    private lateinit var workManager: WorkManager

    init {
        Log.i("hehe", "MainVM: init")
        loadHeroes()
        loadItems()
    }

    val heroInfoList: MutableStateFlow<List<HeroInfoViewEntity>> = MutableStateFlow(emptyList())
    val itemInfoList: MutableStateFlow<ItemWrap> =
        MutableStateFlow<ItemWrap>(ItemWrap(item = Item.getEmpty()))

    fun loadHeroes() {
        val mapper = HeroInfoMapper()
        viewModelScope.launch {
            val response = try {
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

    fun loadItems() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.itemsApi.getItemsInfo()
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
                itemInfoList.emit(list)
            } else {
                Log.w("hehe", "MainVM: Loading heroes failed! Code: ${response.code()}")
            }
        }
    }

    fun loadAppData(context: Context) {
        //workManager = WorkManager.getInstance(context)
        val toast = Toast.makeText(context, "App Data Loaded", Toast.LENGTH_SHORT)
        viewModelScope.launch {
            Log.i("hehe", "MainVM: requesting worker to run ${heroInfoList.value.size} requests")
            downloadHeroImages()
            downloadItemImages()
            toast.show()
        }
    }

    private fun downloadHeroImages() {
        for (hero in heroInfoList.value) {
            loadHeroImageUseCase.execute(hero.image, hero.id.toString())
        }
    }

    private fun downloadItemImages() {
        //for (itemWrap in itemInfoList.value) {
            val item = itemInfoList.value.item
            loadItemImageUseCase.execute(imageUrl = item.image, itemId = item.id.toString())
        //}
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
