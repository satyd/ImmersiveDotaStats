package com.levp.immersivedotastats.presentation.screens.live

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.levp.LiveProMatchesSubscription
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import com.levp.immersivedotastats.data.repository.ApolloStratzApiClientImpl
import com.levp.immersivedotastats.domain.model.live.LiveMatchInfo
import com.levp.immersivedotastats.domain.model.live.LiveMatchLeague
import com.levp.immersivedotastats.domain.repository.StatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveMatchesViewModel @Inject constructor(
    private val apolloStratzApiClient: StratzApiClient,
    val apolloClient: ApolloClient
) : ViewModel() {
    init {
        getLeagueIds()
    }

    val leagueList: MutableStateFlow<List<LiveMatchLeague>> = MutableStateFlow(emptyList())
    val leagueId: MutableStateFlow<Int> = MutableStateFlow(0)

    val matchList: MutableStateFlow<List<LiveMatchInfo>> = MutableStateFlow(emptyList())

    fun getLeagueIds() {
        viewModelScope.launch {
            val leagues = apolloStratzApiClient.getLiveLeagues()
            leagueList.emit(leagues)
            leagueId.emit(leagues[0].leagueId ?: 0)
            delay(100)
            Log.i("LiveVM: ", "${leagues}")
            getSubscription()
        }
    }

    fun getSubscription() {
        val leagueId = leagueList.value.map { it.leagueId }[0] ?: 0
        val sub = apolloClient.subscription(LiveProMatchesSubscription(leagueId)).toFlow()
        Log.i("LiveVM", "get subscription call with ${leagueId} got ${sub}")
        viewModelScope.launch {
            //
        }
    }
}
