package com.levp.immersivedotastats.domain.use_case

import com.levp.immersivedotastats.domain.model.HistoryMatch
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import javax.inject.Inject

class GetUserRecentMatchesUseCase @Inject constructor(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): List<HistoryMatch> {
        return stratzApiClient.getRecentMatches(accountId)
    }
}