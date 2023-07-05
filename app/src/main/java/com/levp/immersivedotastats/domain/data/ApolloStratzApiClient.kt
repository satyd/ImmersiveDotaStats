package com.levp.immersivedotastats.domain.data

import com.apollographql.apollo3.ApolloClient

class ApolloStratzApiClient(
    private val apolloClient: ApolloClient
) {
    /*val client = ApolloClient.builder()
        .serverUrl("")
        .addCustomScalarAdapter(YourType.type, ...)
        .build()*/
}