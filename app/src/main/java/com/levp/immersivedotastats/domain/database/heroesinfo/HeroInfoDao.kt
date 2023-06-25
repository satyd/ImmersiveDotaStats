package com.levp.immersivedotastats.domain.database.heroesinfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface HeroInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroInfo(heroInfo: HeroInfoEntity)

}
