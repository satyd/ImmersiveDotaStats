package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient

class GetUserHeroesPerformanceUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long, isTurboEnabled: Boolean): List<HeroPerformanceStat> {
        val gameModeIds: List<Int> =
            if (isTurboEnabled) {
                listOf(1, 2, 3, 4, 5, 22, 23)
            } else {
                listOf(1, 2, 3, 4, 5, 22)
            }
        return stratzApiClient.getUserHeroesPerformance(accountId, gameModeIds)
    }
}