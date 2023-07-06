package com.levp.immersivedotastats.domain.network.interfaces

import com.levp.AccountInfoQuery
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState

interface StratzApiClient {
    suspend fun getAccountInfo(accountId: Long): UserInfoState
}