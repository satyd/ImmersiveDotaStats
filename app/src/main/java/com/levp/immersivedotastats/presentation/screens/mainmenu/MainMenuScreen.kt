package com.levp.immersivedotastats.presentation.screens.mainmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.levp.immersivedotastats.R
import com.levp.immersivedotastats.presentation.NavDestinations
import com.levp.immersivedotastats.presentation.common.MainSpacer
import com.levp.immersivedotastats.presentation.theme.StatsTheme
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun MainMenuScreen(
    viewModel: MainViewModel = singleViewModel(),
    navController: NavController,
) {
    val appContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StatsTheme.colors.mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.UserInfoScreen.path) },
            text = stringResource(id = R.string.player_info_btn)
        )
        MainSpacer()
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.HeroesInfoScreen.path) },
            text = stringResource(id = R.string.hero_info_btn)
        )
        MainSpacer()
        MainMenuButton(
            onClick = {
                viewModel.loadHeroImages(appContext)
            },
            text = stringResource(id = R.string.load_data_btn)
        )
        MainSpacer()
        MainMenuButton(
            onClick = {

            },
            text = stringResource(id = R.string.settings_btn)
        )
    }
}