package com.levp.immersivedotastats.presentation.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Button(
            modifier = Modifier.size(96.dp, 56.dp),
            onClick = { navController.navigate(NavDestinations.UserInfo.path) },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Search player", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            modifier = Modifier.size(96.dp, 56.dp),
            onClick = { navController.navigate(NavDestinations.HeroesInfo.path) },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Heroes info")
        }
    }

}