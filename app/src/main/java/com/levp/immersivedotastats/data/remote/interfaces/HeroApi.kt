package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.data.remote.dto.heroinfo.HeroStatsInfoItem
import retrofit2.Response

import retrofit2.http.GET

interface HeroApi {

    @GET("/api/heroStats")
    suspend fun getHeroStatsInfo() : Response<List<HeroStatsInfoItem>>

}