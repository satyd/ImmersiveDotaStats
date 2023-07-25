package com.levp.immersivedotastats.data.database.heroesinfo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HeroInfoEntity::class],
    version = 1
)
abstract class HeroDatabase : RoomDatabase() {
    abstract val heroInfoDao: HeroInfoDao
}
