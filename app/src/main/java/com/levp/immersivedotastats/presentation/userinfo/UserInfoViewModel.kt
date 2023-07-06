package com.levp.immersivedotastats.presentation.userinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.immersivedotastats.App
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.dto.playerinfo.Profile
import com.levp.immersivedotastats.domain.usecases.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    companion object {
        const val USER_ID = "USER_ID"
        const val IS_USER_ID_SAVED = "IS_USER_ID_SAVED"
    }

    private val mutableUiState = MutableStateFlow(UserInfoState.getEmpty())
    val uiState = mutableUiState.asStateFlow()

    fun loadUserInfoStratz(newUserId: String) {
        val userId = newUserId.toLong()
        viewModelScope.launch {
            mutableUiState.update {
                it.copy(isLoading = true)
            }
            mutableUiState.update {
                it.copy(
                    userInfo = getUserInfoUseCase.execute(userId),
                    isLoading = false
                )
            }
        }
    }

    fun loadUserInfoOpenDota(newUserId: String) {
        viewModelScope.launch {
            val response = try {
                Log.i("hehe", "trying to get response with $newUserId")
                RetrofitInstance.playerApi.getPlayerById(newUserId)
            } catch (e: IOException) {
                Log.e("hehe", "IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e("hehe", "no Internet")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val profile = response.body()!!.profile
                setUiStateFromProfileData(profile = profile, userId = newUserId)
                App.instance.preferencesManager.saveString(USER_ID, newUserId)
                App.instance.preferencesManager.saveBoolean(IS_USER_ID_SAVED, true)
            } else {
                Log.w("hehe", "Response was not successful!!! code = ${response.code()}")
            }
        }
    }

    private suspend fun setUiStateFromProfileData(profile: Profile, userId: String) {
        mutableUiState.emit(
            uiState.value.copy(
                userInfo = UserInfo(
                    userId = userId,
                    userIcon = profile.avatarFull,
                    userName = profile.personaName,
                    countryCode = profile.locCountryCode,
                    isDotaPlusSub = profile.plus,
                    matches = 0,
                    wins = 0
                )
            )
        )
    }
}
