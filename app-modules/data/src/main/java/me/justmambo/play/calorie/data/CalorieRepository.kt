package me.justmambo.play.calorie.data

import kotlinx.coroutines.flow.Flow
import me.justmambo.play.calorie.data.models.Calorie
import me.justmambo.play.calorie.data.models.Resource
import me.justmambo.play.calorie.local.models.CalorieEntity
import me.justmambo.play.calorie.remote.models.ItemsDto
import me.justmambo.play.calorie.remote.models.NetworkResult

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Fri 24 Feb 2023
 */
interface CalorieRepository {

    fun getCalories(): Flow<List<Calorie>>

    fun getCalorie(name: String) : Flow<Calorie>

    suspend fun searchCalories(query: String) : Resource

}