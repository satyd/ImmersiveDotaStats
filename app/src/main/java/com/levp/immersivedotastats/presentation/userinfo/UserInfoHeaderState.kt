package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.runtime.Stable

@Stable
data class UserInfoHeaderState(
    var profilePicLink: String,
    var profileName: String,
    var countryCode: String = "",
    var isDotaPlusSub: Boolean = true
)
