package com.levp.immersivedotastats.domain.database.heroesinfo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class HeroInfoEntity(
    @PrimaryKey
    val id: Int,
    val locName: String,
    val primaryAttribute: String,
    @Ignore
    val roles: List<String>,
    val localImage: String,
    val legs: Int
)
