package com.levp.immersivedotastats.data.repository

import com.levp.immersivedotastats.data.database.heroesinfo.HeroInfoDao
import com.levp.immersivedotastats.data.database.heroesinfo.HeroInfoEntity
import com.levp.immersivedotastats.domain.repository.StatsRepository

class StatsRepositoryImpl(
    private val dao: HeroInfoDao
) : StatsRepository {

    override suspend fun insertHeroInfo(heroInfoEntity: HeroInfoEntity) {
        dao.insertHeroInfo(heroInfo = heroInfoEntity)
    }

    override suspend fun insertAllHeroesInfo(listOfHeroes: List<HeroInfoEntity>) {
        listOfHeroes.forEach {
            dao.insertHeroInfo(it)
        }
    }
}
