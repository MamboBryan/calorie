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
    @SerialName("name") var name: String,
    @SerialName("calories") var calories: Double,
    @SerialName("serving_size_g") var servingSizeGrams: Double,
    @SerialName("fat_total_g") var fatTotalGrams: Double,
    @SerialName("fat_saturated_g") var fatSaturatedGrams: Double,
    @SerialName("protein_g") var proteinGrams: Double,
    @SerialName("sodium_mg") var sodiumMilligrams: Double,
    @SerialName("potassium_mg") var potassiumMilligrams: Double,
    @SerialName("cholesterol_mg") var cholesterolMilligrams: Double,
    @SerialName("carbohydrates_total_g") var carbohydratesTotalGrams: Double,
    @SerialName("fiber_g") var fiberGrams: Double,
    @SerialName("sugar_g") var sugarGrams: Double
)