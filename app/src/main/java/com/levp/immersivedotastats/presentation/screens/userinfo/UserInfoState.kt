package com.levp.immersivedotastats.presentation.screens.userinfo

import androidx.compose.runtime.Stable
import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo

@Stable
data class UserInfoState(
    val isLoading: Boolean = false,
    val isTurboEnabled: Boolean = true,
    var isUserInitialized: Boolean = false,
    var userInfo: UserInfo,
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
