package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchLeague
import kotlinx.coroutines.flow.Flow

interface StratzApiClient {
    suspend fun getAccountInfo(accountId: Long): UserInfo

    suspend fun getUserHeroesPerformance(
        accountId: Long,
        gameModeIDs: List<Int>
    ): List<HeroPerformanceStat>

    suspend fun getRecentMatches(accountId: Long): List<HistoryMatch>

    suspend fun getLiveLeagues(): List<LiveMatchLeague>

    fun liveSubscription(id: Int): Flow<LiveMatchInfo?>
}