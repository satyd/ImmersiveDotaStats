package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient

class GetUserInfoUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): UserInfo {
        return stratzApiClient.getAccountInfo(accountId)
    }
}