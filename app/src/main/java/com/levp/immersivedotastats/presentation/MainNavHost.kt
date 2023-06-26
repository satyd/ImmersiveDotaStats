package com.levp.immersivedotastats.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levp.immersivedotastats.presentation.heroesinfo.HeroesInfoScreen
import com.levp.immersivedotastats.presentation.mainmenu.MainMenuScreen
import com.levp.immersivedotastats.presentation.userinfo.PlayerInfoScreen

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
        composable(route = NavDestinations.UserInfo.path){
            PlayerInfoScreen()
        }
        composable(route = NavDestinations.HeroesInfo.path){
            HeroesInfoScreen()
        }
    }
}

class NavigationGraph(private val navController: NavController) {

}

enum class NavDestinations(val path: String) {
    MainMenu("main_menu"),
    UserInfo("user_info"),
    HeroesInfo("heroes_info")
}