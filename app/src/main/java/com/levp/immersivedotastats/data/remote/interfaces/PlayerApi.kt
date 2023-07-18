package com.levp.immersivedotastats.data.remote.interfaces

import com.levp.immersivedotastats.data.remote.dto.playerinfo.PlayerInfoRaw
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerApi {

    @GET("/api/players/{player_id}?json")
    suspend fun getPlayerById(@Path("player_id") id : String) : Response<PlayerInfoRaw>

}