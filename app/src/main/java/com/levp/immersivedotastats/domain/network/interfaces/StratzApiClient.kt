package com.levp.immersivedotastats.domain.network.interfaces

import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo

interface StratzApiClient {
    suspend fun getAccountInfo(accountId: Long): UserInfo
    suspend fun getUserHeroesPerformance(accountId: Long, gameModeIDs: List<Int>): List<HeroPerformanceStat>
}