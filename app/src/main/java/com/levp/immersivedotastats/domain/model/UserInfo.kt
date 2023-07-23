package com.levp.immersivedotastats.domain.model


data class UserInfo(
    var userId: String = "",
    var userIcon: String,
    var userName: String,
    var matches: Int,
    var wins: Int,
    var countryCode: String = "",
    var seasonRank: Byte = 0,
    var isDotaPlusSub: Boolean = false
) {
    companion object {
        fun getEmpty(): UserInfo {
            return UserInfo(
                userIcon = "https://http.cat/102",
                userName = "Un Gato",
                matches = 0,
                wins = 0
            )
        }
    }
}

