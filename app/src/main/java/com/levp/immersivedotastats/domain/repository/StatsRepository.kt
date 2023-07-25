package com.levp.immersivedotastats.domain.repository

import com.levp.immersivedotastats.data.database.heroesinfo.HeroInfoEntity

interface StatsRepository {
    suspend fun insertHeroInfo(heroInfoEntity: HeroInfoEntity)

    suspend fun insertAllHeroesInfo(listOfHeroes: List<HeroInfoEntity>)
}
