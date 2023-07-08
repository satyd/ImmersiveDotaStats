package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient

class GetUserHeroesPerformanceUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): List<HeroPerformanceStat> {
        return stratzApiClient.getUserHeroesPerformance(accountId)
    }
}