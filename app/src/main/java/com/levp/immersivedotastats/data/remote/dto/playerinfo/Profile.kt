package com.levp.immersivedotastats.data.remote.dto.playerinfo

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("account_id")
    val accountId: Int,
    val avatar: String,
    @SerializedName("avatarfull")
    val avatarFull: String,
    @SerializedName("avatarmedium")
    val avatarMedium: String,
    val cheese: Int,
    @SerializedName("last_login")
    val lastLogin: String,
    @SerializedName("loccountrycode")
    val locCountryCode: String,
    val name: String,
    @SerializedName("personaname")
    val personaName: String,
    val plus: Boolean,
    @SerializedName("profileurl")
    val profileUrl: String,
    @SerializedName("steamid")
    val steamId: String
)
