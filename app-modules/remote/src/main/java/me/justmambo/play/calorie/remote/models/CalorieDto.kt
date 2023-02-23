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
data class CalorieDto(
    @SerialName("name") val name: String,
    @SerialName("calories") val calories: Double,
    @SerialName("serving_size_g") val servingSizeGrams: Double,
    @SerialName("fat_total_g") val fatTotalGrams: Double,
    @SerialName("fat_saturated_g") val fatSaturatedGrams: Double,
    @SerialName("protein_g") val proteinGrams: Double,
    @SerialName("sodium_mg") val sodiumMilligrams: Double,
    @SerialName("potassium_mg") val potassiumMilligrams: Double,
    @SerialName("cholesterol_mg") val cholesterolMilligrams: Double,
    @SerialName("carbohydrates_total_g") val carbohydratesTotalGrams: Double,
    @SerialName("fiber_g") val fiberGrams: Double,
    @SerialName("sugar_g") val sugarGrams: Double
)