package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.data.remote.dto.UserInfo
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient

class GetUserInfoUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): UserInfo {
        return stratzApiClient.getAccountInfo(accountId)
    }
}