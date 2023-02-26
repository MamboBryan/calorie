package me.justmambo.play.calorie.data.mappers

import me.justmambo.play.calorie.data.models.Calorie
import me.justmambo.play.calorie.local.models.CalorieEntity
import me.justmambo.play.calorie.remote.models.CalorieDto

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Fri 24 Feb 2023
 */
fun CalorieDto?.toUiModel() = this?.let {
    Calorie(
        name,
        calories,
        servingSizeGrams,
        fatTotalGrams,
        fatSaturatedGrams,
        proteinGrams,
        sodiumMilligrams,
        potassiumMilligrams,
        cholesterolMilligrams,
        carbohydratesTotalGrams,
        fiberGrams,
        sugarGrams
    )
}

fun CalorieEntity?.toUiModel() = this?.let {
    Calorie(
        name,
        calories,
        servingSizeGrams,
        fatTotalGrams,
        fatSaturatedGrams,
        proteinGrams,
        sodiumMilligrams,
        potassiumMilligrams,
        cholesterolMilligrams,
        carbohydratesTotalGrams,
        fiberGrams,
        sugarGrams
    )
}

fun Calorie?.toDatabaseModel() = this?.let {
    CalorieEntity(
        name,
        calories,
        servingSizeGrams,
        fatTotalGrams,
        fatSaturatedGrams,
        proteinGrams,
        sodiumMilligrams,
        potassiumMilligrams,
        cholesterolMilligrams,
        carbohydratesTotalGrams,
        fiberGrams,
        sugarGrams
    )
}