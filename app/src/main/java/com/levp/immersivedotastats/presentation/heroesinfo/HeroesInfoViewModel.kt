package com.levp.immersivedotastats.presentation.heroesinfo

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import com.levp.immersivedotastats.utils.HeroInfoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HeroesInfoViewModel @Inject constructor(
    private val repository: HeroInfoRepository
) : ViewModel() {
    init {
        Log.i("hehe", "heroes info VM init")
        loadHeroesToDb()
    }

    fun loadHeroesToDb() {
        val mapper = HeroInfoMapper()
        val heroViewEntityList = mutableStateListOf<HeroInfoViewEntity>()
        viewModelScope.launch {
            val response = try {
                Log.i("hehe", "trying to get heroes response")
                RetrofitInstance.heroApi.getHeroStatsInfo()
            } catch (e: IOException) {
                Log.e("hehe", "IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e("hehe", "No Internet!")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val list = response.body()!!
                heroViewEntityList.addAll(mapper.mapList(list))
            } else {
                Log.w("hehe", "Loading heroes failed! Code: ${response.code()}")
            }
        }
    }
}
