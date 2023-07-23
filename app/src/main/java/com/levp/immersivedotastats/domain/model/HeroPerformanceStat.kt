package com.levp.immersivedotastats.domain.model

data class HeroPerformanceStat(
    val heroId: String,
    val heroName: String,
    val lastPlayed: String,
    val matches: Int,
    val wins: Int,
    val winRate: Float,
    val kDA: Float,
) {
    companion object {
        fun getEmpty() = HeroPerformanceStat(
            heroId = "1",
            heroName = "hero name",
            lastPlayed = "never",
            matches = 0,
            wins = 0,
            winRate = 0f,
            kDA = 0f
        )
    }
}
