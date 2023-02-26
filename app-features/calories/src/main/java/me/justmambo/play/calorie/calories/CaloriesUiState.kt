package me.justmambo.play.calorie.calories

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
sealed class CaloriesUiState {
    object Loading : CaloriesUiState()
    data class Error(val message: String) : CaloriesUiState()
    object Empty : CaloriesUiState()
    object Success : CaloriesUiState()
}