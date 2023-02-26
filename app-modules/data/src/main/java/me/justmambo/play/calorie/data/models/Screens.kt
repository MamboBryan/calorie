package me.justmambo.play.calorie.data.models

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
sealed class Screens(val route: String) {
    object Calories : Screens(route = "calories")
    object Calorie : Screens(route = "calorie")
}