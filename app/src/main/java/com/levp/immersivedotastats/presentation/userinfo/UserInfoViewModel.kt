package com.levp.immersivedotastats.presentation.userinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(): ViewModel() {

    var userId: MutableStateFlow<String> = MutableStateFlow("350885037")
    var playerInfo = MutableStateFlow("there have to be user info")
    var imageUrl = MutableStateFlow("https://www.example.com/image.jpg")

    fun loadUserInfo() {
        viewModelScope.launch {
            val response = try {
                Log.i("hehe", "trying to get response with ${userId.value}")
                RetrofitInstance.playerApi.getPlayerById(userId.value.toInt())
            } catch (e: IOException) {
                Log.e("hehe", "IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e("hehe", "no Internet")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                playerInfo.emit(response.body()!!.toString())
                imageUrl.emit(response.body()!!.profile.avatarFull)
            } else {
                Log.w("hehe", "Response was not successful!!! code = ${response.code()}")
            }
        }
    }

    fun getUserInfo(): String {
        return playerInfo.value
    }
}