package com.levp.immersivedotastats.presentation.screens.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.levp.immersivedotastats.presentation.NavDestinations
import com.levp.immersivedotastats.presentation.common.MainSpacer
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun MainMenuScreen(
    viewModel: MainViewModel = singleViewModel(),
    navController: NavController,
) {
    val appContext = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.UserInfoScreen.path) },
            text = "Profile"
        )
        MainSpacer()
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.HeroesInfoScreen.path) },
            text = "Heroes Info"
        )
        MainSpacer()
        MainMenuButton(
            onClick = {
                viewModel.loadHeroImages(appContext)
            },
            text = "Load Images"
        )
    }

}