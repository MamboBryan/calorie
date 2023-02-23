package me.justmambo.play.calorie.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Entity(tableName = "calories")
data class CalorieEntity(
    @PrimaryKey
    val name: String = UUID.randomUUID().toString(),
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