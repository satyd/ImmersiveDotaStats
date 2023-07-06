package com.levp.immersivedotastats.data

import com.apollographql.apollo3.ApolloClient
import com.levp.AccountInfoQuery
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState
import com.levp.immersivedotastats.utils.toUserInfo

class ApolloStratzApiClient(
    private val apolloClient: ApolloClient
) : StratzApiClient {
    /*val client = ApolloClient.builder()
        .serverUrl("")
        .addCustomScalarAdapter(YourType.type, ...)
        .build()*/
    override suspend fun getAccountInfo(accountId: Long): UserInfoState {
        val response = apolloClient.query(AccountInfoQuery(accountId)).execute().data?.player
        return response?.toUserInfo() ?: UserInfoState.getEmpty()
    }
}