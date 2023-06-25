package com.levp.immersivedotastats.domain.database.heroesinfo

interface HeroInfoRepository {
    suspend fun insertHeroInfo(heroInfoEntity: HeroInfoEntity)
}