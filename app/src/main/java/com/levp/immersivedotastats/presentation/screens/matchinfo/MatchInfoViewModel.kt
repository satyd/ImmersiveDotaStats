package com.levp.immersivedotastats.presentation.screens.matchinfo

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
class MatchInfoViewModel @Inject constructor(
) : ViewModel() {

    companion object {
        const val USER_ID = "USER_ID"
    }

    private val mutableUiState = MutableStateFlow(setEmptyState())
    val matchState = mutableUiState.asStateFlow()

    fun setMatchId(matchId: Long) {
        mutableUiState.update {
            it.copy(
                matchId = matchId
            )
        }
    }

    private fun setEmptyState(): MatchInfoState {
        return MatchInfoState(
            matchId = 0
        )
    }
}
