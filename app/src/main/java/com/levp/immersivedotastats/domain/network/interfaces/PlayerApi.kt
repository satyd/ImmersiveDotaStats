package com.levp.immersivedotastats.domain.network.interfaces

import com.levp.immersivedotastats.domain.network.dto.playerinfo.PlayerInfoRaw
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerApi {

    @GET("/api/players/{player_id}?json")
    suspend fun getPlayerById(@Path("player_id") id : Int) : Response<PlayerInfoRaw>

}