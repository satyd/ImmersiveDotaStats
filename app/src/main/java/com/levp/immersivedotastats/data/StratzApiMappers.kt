package com.levp.immersivedotastats.data

import com.levp.AccountInfoQuery
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState

fun AccountInfoQuery.Player.toUserInfo(): UserInfo {
    return if (this.steamAccount != null) {
        UserInfo(
            userId = steamAccount.id.toString(),
            userIcon = steamAccount.avatar ?: "",
            userName = steamAccount.name ?: "",
            matches = matchCount ?: 0,
            wins = winCount ?: 0,
            seasonRank = steamAccount.seasonRank.toString().toByte() ?: 0,
            isDotaPlusSub = steamAccount.isDotaPlusSubscriber ?: false
        )
    } else {
        UserInfo.getEmpty()
    }
}
