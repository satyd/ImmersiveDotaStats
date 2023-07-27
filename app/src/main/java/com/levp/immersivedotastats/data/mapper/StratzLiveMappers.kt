package com.levp.immersivedotastats.data.mapper

import android.util.Log
import com.levp.LiveMatchesQuery
import com.levp.LiveProMatchesSubscription
import com.levp.immersivedotastats.domain.model.live.LiveMatchInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchLeague

fun LiveMatchesQuery.Match.toLiveMatchLeague(): LiveMatchLeague {
    return with(this) {
        LiveMatchLeague(
            leagueId = leagueId,
            matchId = matchId.toString(),
            radiantScore = radiantScore.toString(),
            direScore = direScore.toString(),
        )
    }
}

fun LiveProMatchesSubscription.MatchLiveLeague.toLiveMatchInfo(): LiveMatchInfo {
    Log.i("hehehe","tranforming $this")
    return with(this) {
        LiveMatchInfo(
            matchId = matchId.toString(),
            radiantScore = radiantScore.toString(),
            direScore = direScore.toString(),
            leagueName = league?.displayName.toString(),
            leagueImage = league?.imageUri.toString(),
            gameMinute = gameMinute.toString(),
            radiantTeam = radiantTeam?.name.toString(),
            direTeam = direTeam?.name.toString(),
            delay = delay.toString().toByte()

        )
    }
}