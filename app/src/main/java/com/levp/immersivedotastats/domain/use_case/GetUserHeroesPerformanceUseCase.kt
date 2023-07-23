package com.levp.immersivedotastats.domain.use_case

import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import javax.inject.Inject

class GetUserHeroesPerformanceUseCase @Inject constructor(
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