package com.levp.immersivedotastats.data

import com.levp.AccountInfoQuery
import com.levp.GetHeroesPerformanceQuery
import com.levp.GetRecentMatchesQuery
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.domain.network.dto.HistoryMatch
import com.levp.immersivedotastats.domain.network.dto.UserInfo
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
            heroId = heroId.toString(),
            heroName = hero?.displayName ?: "",
            matches = matchCount,
            wins = winCount,
            winRate = (matchCount.toFloat() / winCount),
            kDA = BigDecimal(kDA ?: 0.0).setScale(2, RoundingMode.HALF_EVEN).toFloat(),
            lastPlayed = lastPlayedDateTime.toString()
        )
    }
}

fun GetRecentMatchesQuery.Match.toHistoryMatch(): HistoryMatch {
    return with(this) {
        HistoryMatch(
            matchId = id.toString().toLong(),
            rank = rank ?: 0,
            durationSeconds = durationSeconds ?: 0,
            isRadiant = players?.get(0)?.isRadiant ?: true,
            isVictory = players?.get(0)?.isVictory ?: true,
            heroId = players?.get(0)?.heroId.toString(),
            heroName = players?.get(0)?.hero?.displayName ?: "",
            kills = players?.get(0)?.kills.toString().toShort(),
            deaths = players?.get(0)?.deaths.toString().toShort(),
            assists = players?.get(0)?.assists.toString().toShort(),
            imp = players?.get(0)?.imp.toString().toShort(),
            heroDamage = players?.get(0)?.heroDamage.toString(),
            heroHealing = players?.get(0)?.heroHealing.toString(),
            towerDamage = players?.get(0)?.towerDamage.toString(),
            award = ""
        )
    }
}
