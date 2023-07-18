package com.levp.immersivedotastats.data.remote.dto

data class HistoryMatch(
    val matchId: Long,
    val rank: Int,
    val durationSeconds: Int,
    val isRadiant: Boolean,
    val isVictory: Boolean,
    val heroId: String,
    val heroName: String,
    val kills: Short,
    val deaths: Short,
    val assists: Short,
    val imp: Short,
    val heroDamage: String,
    val heroHealing: String,
    val towerDamage: String,
    val award: String
) {
    companion object {
        fun getEmpty() = HistoryMatch(
            matchId = 0,
            rank = 0,
            durationSeconds = 0,
            isRadiant = true,
            isVictory = true,
            heroId = "-1",
            heroName = "",
            kills = 0,
            deaths = 0,
            assists = 0,
            imp = 0,
            heroDamage = "0",
            heroHealing = "0",
            towerDamage = "0",
            award = ""
        )
    }
}
