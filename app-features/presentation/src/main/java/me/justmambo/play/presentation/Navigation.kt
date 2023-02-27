package me.justmambo.play.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.justmambo.play.calorie.calories.CaloriesScreen
import me.justmambo.play.calorie.data.models.Screens
import me.justmambo.play.calorie.item.CalorieScreen

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Calories.route) {
        composable(route = Screens.Calories.route) {
            CaloriesScreen(navController = navController)
        }
        composable(
            route = Screens.Calorie.route.plus("/{id}"),
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            CalorieScreen(navController = navController)
        }
    }

}