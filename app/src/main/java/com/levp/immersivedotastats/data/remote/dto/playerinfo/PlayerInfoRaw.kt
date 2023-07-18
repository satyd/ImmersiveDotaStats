package com.levp.immersivedotastats.data.remote.dto.playerinfo

import com.google.gson.annotations.SerializedName

data class PlayerInfoRaw(
    @SerializedName("competitive_rank")
    val competitiveRank: Int,
    @SerializedName("leaderboard_rank")
    val leaderboardRank: Int,
    @SerializedName("mmr_estimate")
    val mmrEstimate: MmrEstimate,
    val profile: Profile,
    val rankTier: Int,
    @SerializedName("solo_competitive_rank")
    val soloCompetitiveRank: Int
)
