package com.levp.immersivedotastats.domain.usecases

import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState

class GetUserInfoUseCase(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): UserInfoState {
        return stratzApiClient.getAccountInfo(accountId)
    }
}