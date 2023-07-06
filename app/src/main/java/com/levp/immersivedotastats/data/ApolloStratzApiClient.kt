package com.levp.immersivedotastats.data

import com.apollographql.apollo3.ApolloClient
import com.levp.AccountInfoQuery
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState

class ApolloStratzApiClient(
    private val apolloClient: ApolloClient
) : StratzApiClient {
    /*val client = ApolloClient.builder()
        .serverUrl("")
        .addCustomScalarAdapter(YourType.type, ...)
        .build()*/
    override suspend fun getAccountInfo(accountId: Long): UserInfo {
        val response = apolloClient.query(AccountInfoQuery(accountId)).execute().data?.player
        return response?.toUserInfo() ?: UserInfo.getEmpty()
    }
}