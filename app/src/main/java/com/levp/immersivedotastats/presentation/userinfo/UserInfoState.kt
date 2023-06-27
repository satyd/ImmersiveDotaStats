package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.runtime.Stable

@Stable
data class UserInfoState(
    var userId: String = "",
    var profilePicLink: String,
    var profileName: String,
    var countryCode: String = "",
    var isDotaPlusSub: Boolean = false
) {
    companion object{
        fun getEmpty(): UserInfoState {
            return UserInfoState(
                profilePicLink = "https://http.cat/102",
                profileName = "Catoracle"
            )
        }
    }
}
