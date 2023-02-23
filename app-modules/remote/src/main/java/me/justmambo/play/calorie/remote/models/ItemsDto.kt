package me.justmambo.play.calorie.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Serializable
data class ItemsDto(
    @SerialName("items") var items: List<CalorieDto>
)