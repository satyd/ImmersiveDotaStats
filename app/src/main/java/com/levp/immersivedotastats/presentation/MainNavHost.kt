package com.levp.immersivedotastats.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.levp.immersivedotastats.presentation.screens.heroesinfo.HeroesInfoScreen
import com.levp.immersivedotastats.presentation.screens.mainmenu.MainMenuScreen
import com.levp.immersivedotastats.presentation.screens.matchinfo.MatchInfoScreen
import com.levp.immersivedotastats.presentation.screens.userinfo.UserInfoScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavDestinations.MainMenu
    ) {
        composable(route = NavDestinations.MainMenu) {
            MainMenuScreen(navController = navController)
        }
        composable(route = NavDestinations.UserInfoScreen) {
            UserInfoScreen(navController = navController)
        }
        composable(route = NavDestinations.HeroesInfoScreen) {
            HeroesInfoScreen()
        }
        composable(
            route = NavDestinations.MatchInfoScreen +
                    "?${ArgumentKeys.MatchId}={${ArgumentKeys.MatchId}}",
            arguments = listOf(
                navArgument(ArgumentKeys.MatchId) {
                    type = NavType.LongType
                })
        ) { backStackEntry ->
            val matchId = backStackEntry.arguments?.getLong(ArgumentKeys.MatchId) ?: 0
            MatchInfoScreen(matchId = matchId)
        }
    }
}

class NavigationGraph(private val navController: NavController) {
    val showMatchInfo: (Long) -> Unit = { matchId ->
        navController.navigate(
            NavDestinations.MatchInfoScreen + "?${ArgumentKeys.MatchId}=$matchId"
        )
    }
}

object NavDestinations {
    const val MainMenu = "main_menu"
    const val UserInfoScreen = "user_info"
    const val HeroesInfoScreen = "heroes_info"
    const val MatchInfoScreen = "match_info"
}

object ArgumentKeys {
    const val MatchId = "matchId"
}