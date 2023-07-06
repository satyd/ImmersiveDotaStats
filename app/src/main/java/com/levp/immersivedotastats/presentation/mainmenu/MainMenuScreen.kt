package com.levp.immersivedotastats.presentation.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.levp.immersivedotastats.presentation.NavDestinations

@Composable
fun MainMenuScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.UserInfoScreen.path) },
            text = "Profile"
        )
        Spacer(modifier = Modifier.size(12.dp))
        MainMenuButton(
            onClick = { navController.navigate(NavDestinations.HeroesInfoScreen.path) },
            text = "Heroes Info"
        )
    }

}