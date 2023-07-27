package com.levp.immersivedotastats.domain.model.live

data class LiveMatchInfo(
    val matchId: String,
    val radiantScore: String,
    val direScore: String,
    val leagueName: String,
    val leagueImage: String,
    val gameMinute: String,
    val radiantTeam: String,
    val direTeam: String,
    val delay: Byte
)
