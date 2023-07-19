package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.data.remote.dto.item.Item
import com.levp.immersivedotastats.data.remote.dto.item.ItemWrap
import retrofit2.Response
import retrofit2.http.GET

interface ItemsApi {

    @GET("/api/constants/items")
    suspend fun getItemsInfo() : Response<ItemWrap>

}