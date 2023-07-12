package com.levp.immersivedotastats.data

import com.apollographql.apollo3.ApolloClient
import com.levp.AccountInfoQuery
import com.levp.GetHeroesPerformanceQuery
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient

class ApolloStratzApiClient(
    private val apolloClient: ApolloClient
) : StratzApiClient {

    override suspend fun getAccountInfo(accountId: Long): UserInfo {
        val response = apolloClient.query(AccountInfoQuery(accountId)).execute().data?.player
        return response?.toUserInfo() ?: UserInfo.getEmpty()
    }

    override suspend fun getUserHeroesPerformance(accountId: Long, gameModeIDs: List<Int>): List<HeroPerformanceStat> {
        val performance = apolloClient.query(GetHeroesPerformanceQuery(accountId, gameModeIDs))

            .execute().data?.player?.heroesPerformance
        return performance?.map { it?.toHeroesPerformanceStat() ?: HeroPerformanceStat.getEmpty() }
            ?: emptyList()
    }
}