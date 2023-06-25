package com.levp.immersivedotastats.domain.database.heroesinfo

interface HeroInfoRepository {
    suspend fun insertHeroInfo(heroInfoEntity: HeroInfoEntity)

    suspend fun insertAllHeroesInfo(listOfHeroes: List<HeroInfoEntity>)
}
