package com.levp.immersivedotastats.presentation.screens.userinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.presentation.NavDestinations
import com.levp.immersivedotastats.presentation.NavigationGraph
import com.levp.immersivedotastats.presentation.common.MediumSpacer
import com.levp.immersivedotastats.presentation.common.SmallSpacer
import com.levp.immersivedotastats.presentation.screens.userinfo.components.HeroStatPanel
import com.levp.immersivedotastats.presentation.screens.userinfo.components.RecentMatchesPanel
import com.levp.immersivedotastats.presentation.screens.userinfo.components.UserInfoHeader
import com.levp.immersivedotastats.presentation.theme.ImmersiveDotaStatsTheme
import com.levp.immersivedotastats.presentation.theme.SmallPadding
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import com.levp.immersivedotastats.utils.Constants
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@SuppressWarnings("FunctionNaming")
@Composable
fun UserInfoScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: UserInfoViewModel = singleViewModel(),
) {
    val userInfoState by viewModel.uiState.collectAsState()
    var userId by rememberSaveable { mutableStateOf("350885037") }
    val switchTurboEnabled = { viewModel.isTurboEnabledSwitch() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StatsTheme.colors.mainBackground)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserInfoHeader(userInfoState)
        SmallSpacer()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = stringResource(id = R.string.include_turbo))
            MediumSpacer()
            Switch(
                checked = userInfoState.isTurboEnabled,
                onCheckedChange = {
                    switchTurboEnabled()
                },
                modifier = Modifier
                    .padding(Dp.SmallPadding)
                    .height(24.dp),
                colors = SwitchDefaults.colors(
                    checkedTrackColor = StatsTheme.colors.mainButton
                )
            )
        }
        if (userInfoState.userHeroesPerformance.size >= Constants.HeroStatEntriesToShow) {
            HeroStatPanel(
                isLoading = userInfoState.isLoading,
                userHeroesPerformance = userInfoState.userHeroesPerformance
            )
        }
        SmallSpacer()
        if (userInfoState.userRecentMatches.isNotEmpty()) {
            RecentMatchesPanel(
                isLoading = userInfoState.isLoading,
                userRecentMatches = userInfoState.userRecentMatches,
                onClick = NavigationGraph(navController).showMatchInfo
            )
        }
        SmallSpacer()
        TextField(
            value = userId,
            onValueChange = {
                userId = it
            },
            label = { Text("Search player by ID") }
        )
        SmallSpacer()
        Button(
            onClick = {
                viewModel.loadUserInfoStratz(userId)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = StatsTheme.colors.mainButton,
                contentColor = StatsTheme.colors.mainTextLight
            )
        ) {
            Text(text = stringResource(id = R.string.load_user_btn))
        }
        SmallSpacer()
    }
}


@Preview(showBackground = true)
@Composable
fun UserPreview() {
    ImmersiveDotaStatsTheme {
        UserInfoScreen(rememberNavController())
    }
}