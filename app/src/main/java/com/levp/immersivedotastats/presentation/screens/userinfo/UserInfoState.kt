package com.levp.immersivedotastats.presentation.screens.userinfo

import androidx.compose.runtime.Stable
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.domain.network.dto.UserInfo

@Stable
data class UserInfoState(
    var userInfo: UserInfo,
    val isLoading: Boolean = false,
    val isTurboEnabled: Boolean = true,
    var userHeroesPerformance: List<HeroPerformanceStat>,
    var userRecentMatches: List<HistoryMatch>
) {
    companion object {
        fun getEmpty(): UserInfoState {
            return UserInfoState(
                userInfo = UserInfo.getEmpty(),
                userHeroesPerformance = emptyList(),
                userRecentMatches = emptyList(),
            )
        }
    }
}
