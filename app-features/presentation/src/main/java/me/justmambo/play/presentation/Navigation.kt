package me.justmambo.play.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.justmambo.play.calorie.calories.CaloriesScreen
import me.justmambo.play.calorie.data.models.Screens

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
    }

}