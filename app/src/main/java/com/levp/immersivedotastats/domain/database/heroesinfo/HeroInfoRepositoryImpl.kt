package com.levp.immersivedotastats.domain.database.heroesinfo

class HeroInfoRepositoryImpl(
    private val dao: HeroInfoDao
) : HeroInfoRepository {

    override suspend fun insertHeroInfo(heroInfoEntity: HeroInfoEntity) {
        dao.insertHeroInfo(heroInfo = heroInfoEntity)
    }

}