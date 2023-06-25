package com.levp.immersivedotastats.domain.network.dto.playerinfo

data class PlayerInfoRaw(
    val competitiveRank: Int,
    val leaderboardRank: Int,
    val mmrEstimate: MmrEstimate,
    val profile: Profile,
    val rankTier: Int,
    val soloCompetitiveRank: Int
)