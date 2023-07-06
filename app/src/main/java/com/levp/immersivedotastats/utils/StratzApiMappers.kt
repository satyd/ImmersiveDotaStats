package com.levp.immersivedotastats.utils

import com.benasher44.uuid.nameBasedUuidOf
import com.levp.AccountInfoQuery
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState

fun AccountInfoQuery.Player.toUserInfo(): UserInfoState {
    return if (this.steamAccount != null) {
        UserInfoState(
            userId = steamAccount.id.toString(),
            profilePicLink = steamAccount.avatar ?: "",
            profileName = steamAccount.name ?: "",
            isDotaPlusSub = steamAccount.isDotaPlusSubscriber ?: false
        )
    } else {
        UserInfoState.getEmpty()
    }
}
