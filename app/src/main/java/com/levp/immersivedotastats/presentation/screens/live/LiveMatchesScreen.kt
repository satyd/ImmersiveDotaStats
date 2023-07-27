package com.levp.immersivedotastats.presentation.screens.live

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.apollographql.apollo3.api.ApolloResponse
import com.levp.LiveProMatchesSubscription
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun LiveMatchesScreen(
    viewModel: LiveMatchesViewModel = singleViewModel()
) {
    val client = viewModel.apolloClient
    val leagueId = viewModel.leagueId.collectAsState()
    val matchFlow =
        remember { client.subscription(LiveProMatchesSubscription(leagueId.value)).toFlow() }
    val response: ApolloResponse<LiveProMatchesSubscription.Data>? by matchFlow.collectAsState(
        initial = null
    )
    val (text, onTextChanged) = remember {
        mutableStateOf("kek")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StatsTheme.colors.mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("hehe")
        Button(onClick = { viewModel.getSubscription() }) {
            Text(text = "reload")
        }
        LaunchedEffect(response) {
            Log.i("hehehe","$response")
            if (response == null) return@LaunchedEffect
            val msg = when (response!!.data?.matchLiveLeague) {
                null -> "sub error"
                else -> {
                    response!!.data?.matchLiveLeague?.radiantTeam?.name + " vs "
                    response!!.data?.matchLiveLeague?.direTeam?.name
                }
            }
            onTextChanged(msg.toString())
        }
        Text(text = text)
    }
}