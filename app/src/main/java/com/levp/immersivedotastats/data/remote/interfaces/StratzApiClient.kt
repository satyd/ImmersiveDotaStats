package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.data.remote.dto.HeroPerformanceStat
import com.levp.immersivedotastats.data.remote.dto.HistoryMatch
import com.levp.immersivedotastats.data.remote.dto.UserInfo

interface StratzApiClient {
    suspend fun getAccountInfo(accountId: Long): UserInfo
    suspend fun getUserHeroesPerformance(accountId: Long, gameModeIDs: List<Int>): List<HeroPerformanceStat>
    suspend fun getRecentMatches(accountId: Long): List<HistoryMatch>
}