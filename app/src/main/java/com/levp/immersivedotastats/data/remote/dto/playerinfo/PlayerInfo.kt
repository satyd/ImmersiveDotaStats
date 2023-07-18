package com.levp.immersivedotastats.data.remote.dto.playerinfo

data class PlayerInfo(
    var accountId: Int,
    var name: String,
    var dotaPlusSub: Boolean,
    var avatarUrl: String,
    var countryCode: String,
)
