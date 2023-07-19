package com.levp.immersivedotastats.domain.use_case

import com.levp.immersivedotastats.data.remote.dto.UserInfo
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val stratzApiClient: StratzApiClient
) {
    suspend fun execute(accountId: Long): UserInfo {
        return stratzApiClient.getAccountInfo(accountId)
    }
}