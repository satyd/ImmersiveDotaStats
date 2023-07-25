package com.levp.immersivedotastats.data.database.heroesinfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class HeroInfoEntity(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val heroName: String,
    val heroId: Int,
    val primaryAttribute: String,
    val localImage: String,
    val legs: Int
)
