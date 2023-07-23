package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo

interface StratzApiClient {
    suspend fun getAccountInfo(accountId: Long): UserInfo
    suspend fun getUserHeroesPerformance(accountId: Long, gameModeIDs: List<Int>): List<HeroPerformanceStat>
    suspend fun getRecentMatches(accountId: Long): List<HistoryMatch>
}