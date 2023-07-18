package com.levp.immersivedotastats.presentation.screens.heroesinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.data.repository.RetrofitInstance
import com.levp.immersivedotastats.data.HeroInfoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    val heroInfoList: MutableStateFlow<List<HeroInfoViewEntity>> = MutableStateFlow(emptyList())

    fun loadHeroesToDb() {
        val mapper = HeroInfoMapper()
        viewModelScope.launch {
            val response = try {
                Log.i("hehe", "trying to get heroes response VM")
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
                heroInfoList.emit(mapper.mapList(list))
            } else {
                Log.w("hehe", "Loading heroes failed! Code: ${response.code()}")
            }
        }
    }

    fun getHeroInfo(): List<HeroInfoViewEntity> {
        return heroInfoList.value
    }

}
