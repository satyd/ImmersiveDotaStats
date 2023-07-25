package com.levp.immersivedotastats.presentation.screens.userinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.immersivedotastats.App
import com.levp.immersivedotastats.data.RetrofitInstance
import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo
import com.levp.immersivedotastats.data.remote.dto.playerinfo.Profile
import com.levp.immersivedotastats.domain.use_case.GetUserHeroesPerformanceUseCase
import com.levp.immersivedotastats.domain.use_case.GetUserInfoUseCase
import com.levp.immersivedotastats.domain.use_case.GetUserRecentMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserRecentMatchesUseCase: GetUserRecentMatchesUseCase,
    private val getUserHeroesPerformanceUseCase: GetUserHeroesPerformanceUseCase
) : ViewModel() {

    companion object {
        const val USER_ID = "USER_ID"
        const val IS_USER_ID_SAVED = "IS_USER_ID_SAVED"
        const val IS_TURBO_ENABLED = "IS_TURBO_ENABLED"
    }

    private val mutableUiState = MutableStateFlow(setEmptyState())
    val uiState = mutableUiState.asStateFlow()

    private fun getCurrentUserId(): Long {
        return uiState.value.userInfo.userId.toLong()
    }

    private suspend fun requestUserInfoStratz(newUserId: String) {
        val userId = newUserId.toLong()
        mutableUiState.update {
            it.copy(isLoading = true)
        }

        mutableUiState.update {
            val userInfo = requestUserInfo(userId)
            it.copy(
                userInfo = userInfo,
                userHeroesPerformance = requestHeroesPerformance(userId),
                userRecentMatches = requestMatchHistory(userId),
                isLoading = false,
                isUserInitialized = userInfo.matches > 0
            )
        }
    }

    private suspend fun requestHeroesPerformance(userId: Long): List<HeroPerformanceStat> {
        return getUserHeroesPerformanceUseCase.execute(userId, uiState.value.isTurboEnabled)
            .sortedByDescending { it.matches }
    }

    private suspend fun requestMatchHistory(userId: Long): List<HistoryMatch> {
        return getUserRecentMatchesUseCase.execute(userId)
    }

    private suspend fun requestUserInfo(userId: Long): UserInfo {
        return getUserInfoUseCase.execute(userId)
    }

    fun loadUserInfoStratz(newUserId: String) {
        viewModelScope.launch {
            requestUserInfoStratz(newUserId)
        }
    }

    fun isTurboEnabledSwitch() {
        viewModelScope.launch {
            val isTurboEnabled = !uiState.value.isTurboEnabled
            App.instance.preferencesManager.saveBoolean(IS_TURBO_ENABLED, isTurboEnabled)
            mutableUiState.emit(
                uiState.value.copy(
                    isTurboEnabled = isTurboEnabled
                )
            )
            if (uiState.value.isUserInitialized) {
                requestUserInfoStratz(uiState.value.userInfo.userId)
            }
        }
    }

    fun loadUserInfoOpenDota(newUserId: String) {
        viewModelScope.launch {
            val response = try {
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

    private fun setEmptyState(): UserInfoState {
        return UserInfoState(
            isLoading = false,
            isUserInitialized = false,
            isTurboEnabled = App.instance.preferencesManager.getBoolean(IS_TURBO_ENABLED),
            userInfo = UserInfo.getEmpty(),
            userHeroesPerformance = emptyList(),
            userRecentMatches = emptyList(),
        )
    }
}
