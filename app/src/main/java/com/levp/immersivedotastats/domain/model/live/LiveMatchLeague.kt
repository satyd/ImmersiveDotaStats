package com.levp.immersivedotastats.domain.model.live

data class LiveMatchLeague(
    val leagueId: Int?,
    val matchId: String,
    val radiantScore: String = "",
    val direScore: String = ""
)
