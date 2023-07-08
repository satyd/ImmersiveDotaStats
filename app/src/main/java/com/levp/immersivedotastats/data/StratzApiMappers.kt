package com.levp.immersivedotastats.data

import com.levp.AccountInfoQuery
import com.levp.GetHeroesPerformanceQuery
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.UserInfo
import com.levp.immersivedotastats.presentation.userinfo.UserInfoState
import java.math.BigDecimal
import java.math.RoundingMode

fun AccountInfoQuery.Player.toUserInfo(): UserInfo {
    return if (this.steamAccount != null) {
        UserInfo(
            userId = steamAccount.id.toString(),
            userIcon = steamAccount.avatar ?: "",
            userName = steamAccount.name ?: "",
            matches = matchCount ?: 0,
            wins = winCount ?: 0,
            seasonRank = steamAccount.seasonRank.toString().toByte(),
            isDotaPlusSub = steamAccount.isDotaPlusSubscriber ?: false
        )
    } else {
        UserInfo.getEmpty()
    }
}

fun GetHeroesPerformanceQuery.HeroesPerformance.toHeroesPerformanceStat(): HeroPerformanceStat {
    return with(this) {
        HeroPerformanceStat(
            heroName = hero?.displayName ?: "",
            matches = matchCount,
            wins = winCount,
            winRate = (matchCount.toFloat() / winCount),
            kDA = BigDecimal(kDA ?: 0.0).setScale(2, RoundingMode.HALF_EVEN).toFloat(),
            lastPlayed = lastPlayedDateTime.toString()
        )
    }
}