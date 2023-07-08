package com.levp.immersivedotastats.domain.network.dto

data class HeroPerformanceStat(
    val heroName: String,
    val lastPlayed: String,
    val matches: Int,
    val wins: Int,
    val winRate: Float,
    val kDA: Float,
) {
    companion object {
        fun getEmpty() = HeroPerformanceStat(
            heroName = "hero name",
            lastPlayed = "never",
            matches = 0,
            wins = 0,
            winRate = 0f,
            kDA = 0f
        )
    }
}
