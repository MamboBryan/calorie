package me.justmambo.play.calorie.item

import me.justmambo.play.calorie.data.models.Calorie

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Mon 27 Feb 2023
 */
sealed class CalorieUiState {
    object Loading : CalorieUiState()
    object Error : CalorieUiState()
    data class Loaded(val calorie: Calorie) : CalorieUiState()
}