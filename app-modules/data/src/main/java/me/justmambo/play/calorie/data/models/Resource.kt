package me.justmambo.play.calorie.data.models

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Fri 24 Feb 2023
 */
sealed class Resource {
    data class Error(val message: String) : Resource()
    data class Success(val isEmpty: Boolean) : Resource()
}