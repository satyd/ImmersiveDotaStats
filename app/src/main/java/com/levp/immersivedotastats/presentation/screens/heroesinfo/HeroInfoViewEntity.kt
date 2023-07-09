package com.levp.immersivedotastats.presentation.screens.heroesinfo

data class HeroInfoViewEntity(
    val id: Int,
    val locName: String,
    val primaryAttribute: String,
    val roles: List<String>,
    val image: String,
    val legs: Int
)