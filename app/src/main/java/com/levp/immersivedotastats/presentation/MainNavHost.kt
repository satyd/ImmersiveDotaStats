package com.levp.immersivedotastats.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levp.immersivedotastats.presentation.screens.heroesinfo.HeroesInfoScreen
import com.levp.immersivedotastats.presentation.screens.live.LiveMatchesScreen
import com.levp.immersivedotastats.presentation.screens.mainmenu.MainMenuScreen
import com.levp.immersivedotastats.presentation.screens.userinfo.UserInfoScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = NavDestinations.MainMenu.path
    ){
        composable(route = NavDestinations.MainMenu.path){
            MainMenuScreen(navController = navController)
        }
        composable(route = NavDestinations.UserInfoScreen.path){
            UserInfoScreen()
        }
        composable(route = NavDestinations.HeroesInfoScreen.path){
            HeroesInfoScreen()
        }
        composable(route = NavDestinations.LiveScreen.path){
            LiveMatchesScreen()
        }
    }
}

class NavigationGraph(private val navController: NavController) {

}

enum class NavDestinations(val path: String) {
    MainMenu("main_menu"),
    UserInfoScreen("user_info"),
    HeroesInfoScreen("heroes_info"),
    LiveScreen("live_matches"),
}