package me.justmambo.play.calorie.data.models

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Fri 24 Feb 2023
 */
data class Calorie(
    val name: String,
    val calories: Double,
    val servingSizeGrams: Double,
    val fatTotalGrams: Double,
    val fatSaturatedGrams: Double,
    val proteinGrams: Double,
    val sodiumMilligrams: Double,
    val potassiumMilligrams: Double,
    val cholesterolMilligrams: Double,
    val carbohydratesTotalGrams: Double,
    val fiberGrams: Double,
    val sugarGrams: Double
)
