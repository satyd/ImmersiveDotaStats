package com.levp.immersivedotastats.data.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.levp.AccountInfoQuery
import com.levp.GetHeroesPerformanceQuery
import com.levp.GetRecentMatchesQuery
import com.levp.immersivedotastats.data.mapper.toHeroesPerformanceStat
import com.levp.immersivedotastats.data.mapper.toHistoryMatch
import com.levp.immersivedotastats.data.mapper.toUserInfo
import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient



class ApolloStratzApiClientImpl(
    private val apolloClient: ApolloClient
) : StratzApiClient {

    override suspend fun getAccountInfo(accountId: Long): UserInfo {
        val response = apolloClient.query(AccountInfoQuery(accountId)).execute().data?.player
        return response?.toUserInfo() ?: UserInfo.getEmpty()
    }

    override suspend fun getUserHeroesPerformance(
        accountId: Long, gameModeIDs: List<Int>
    ): List<HeroPerformanceStat> {
        val performance = apolloClient.query(GetHeroesPerformanceQuery(accountId, gameModeIDs))
            .execute().data?.player?.heroesPerformance

        return performance?.map { it?.toHeroesPerformanceStat() ?: HeroPerformanceStat.getEmpty() }
            ?: emptyList()
    }

    override suspend fun getRecentMatches(accountId: Long): List<HistoryMatch> {
        return try {
            val recentMatchesResponse =
                apolloClient.query(GetRecentMatchesQuery(accountId)).execute()
            val recentMatches = recentMatchesResponse.data!!.player!!.matches!!
            recentMatches.map { it?.toHistoryMatch() ?: HistoryMatch.getEmpty() }
        } catch (exception: ApolloException) {
            Log.e(apolloStratzApiTag, "$exception")
            emptyList()
        }
    }

    companion object {
        private const val apolloStratzApiTag = "ApolloStratzApiClient: "
    }
}