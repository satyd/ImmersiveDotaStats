package com.levp.immersivedotastats.domain.network.dto.playerinfo

data class Profile(
    val accountId: Int,
    val avatar: String,
    val avatarFull: String,
    val avatarMedium: String,
    val cheese: Int,
    val isContributor: Boolean,
    val isSubscriber: Boolean,
    val lastLogin: String,
    val locCountryCode: String,
    val name: String,
    val personaName: String,
    val plus: Boolean,
    val profileUrl: String,
    val steamId: String
)