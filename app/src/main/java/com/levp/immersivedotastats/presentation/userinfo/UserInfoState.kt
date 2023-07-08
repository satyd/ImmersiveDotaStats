package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.runtime.Stable
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo

@Stable
data class UserInfoState(
    var userInfo: UserInfo,
    val isLoading: Boolean = false,
    var userHeroesPerformance: List<HeroPerformanceStat>
) {
    companion object {
        fun getEmpty(): UserInfoState {
            return UserInfoState(
                userInfo = UserInfo.getEmpty(),
                userHeroesPerformance = emptyList()
            )
        }
    }
}
