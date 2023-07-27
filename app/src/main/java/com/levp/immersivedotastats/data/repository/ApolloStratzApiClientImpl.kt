package com.levp.immersivedotastats.data.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.levp.AccountInfoQuery
import com.levp.GetHeroesPerformanceQuery
import com.levp.GetRecentMatchesQuery
import com.levp.LiveMatchesQuery
import com.levp.LiveProMatchesSubscription
import com.levp.immersivedotastats.domain.model.HeroPerformanceStat
import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.domain.model.UserInfo
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import com.levp.immersivedotastats.data.mapper.toHeroesPerformanceStat
import com.levp.immersivedotastats.data.mapper.toHistoryMatch
import com.levp.immersivedotastats.data.mapper.toLiveMatchInfo
import com.levp.immersivedotastats.data.mapper.toLiveMatchLeague
import com.levp.immersivedotastats.data.mapper.toUserInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchLeague
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.transform

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

    override suspend fun getLiveLeagues(): List<LiveMatchLeague> {
        val liveMatchesLeagues =
            apolloClient.query(LiveMatchesQuery()).execute().data?.live?.matches
        if (liveMatchesLeagues != null) {
            return liveMatchesLeagues.filter { it?.leagueId != null }
                .map { it?.toLiveMatchLeague() ?: return emptyList() }
        }
        return emptyList()

    }

    override fun liveSubscription(id: Int): Flow<LiveMatchInfo?> {
        return try {
            apolloClient.subscription(LiveProMatchesSubscription(id)).toFlow()
                .transform {
                    if (it.data != null) {
                        emit(it.data?.matchLiveLeague?.toLiveMatchInfo())
                    } else {
                        emit(null)
                    }
                }
        } catch (exception: ApolloNetworkException) {
            Log.e(apolloStratzApiTag, "$exception")
            emptyFlow()
        }
    }

    companion object {
        private const val apolloStratzApiTag = "ApolloStratzApiClient: "
    }
}