package com.levp.immersivedotastats.domain.network.interfaces

import com.levp.immersivedotastats.domain.network.dto.heroinfo.HeroStatsInfoItem
import retrofit2.Response

import retrofit2.http.GET

interface HeroApi {

    @GET("/api/heroStats")
    suspend fun getHeroStatsInfo() : Response<List<HeroStatsInfoItem>>

}