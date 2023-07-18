package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient

class GetUserRecentMatchesUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): List<HistoryMatch> {
        return stratzApiClient.getRecentMatches(accountId)
    }
}