package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.data.remote.dto.HistoryMatch
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient

class GetUserRecentMatchesUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): List<HistoryMatch> {
        return stratzApiClient.getRecentMatches(accountId)
    }
}