package me.justmambo.play.calorie.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import me.justmambo.play.calorie.data.mappers.toDatabaseModel
import me.justmambo.play.calorie.data.mappers.toUiModel
import me.justmambo.play.calorie.data.models.Calorie
import me.justmambo.play.calorie.data.models.Resource
import me.justmambo.play.calorie.local.daos.CalorieDao
import me.justmambo.play.calorie.local.models.CalorieEntity
import me.justmambo.play.calorie.remote.CalorieApi
import me.justmambo.play.calorie.remote.models.ItemsDto
import me.justmambo.play.calorie.remote.models.NetworkResult
import javax.inject.Inject

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Fri 24 Feb 2023
 */
class DefaultCalorieRepository (
    val dao: CalorieDao,
    val api: CalorieApi
) : CalorieRepository {

    private val dispatcher = Dispatchers.IO

    override fun getCalories(): Flow<List<Calorie>> =
        dao.searchCalories().map { list -> list.mapNotNull { it.toUiModel() } }

    override fun getCalorie(name: String): Flow<Calorie> =
        dao.getCalorie(name = name).mapNotNull { it.toUiModel() }

    override suspend fun searchCalories(query: String): Resource {
        return withContext(dispatcher) {
            val response = api.getCalories(query = query)
            if (response.isSuccessful.not()) return@withContext Resource.Error(message = response.message)
            val calories = response.data?.items
            if (calories.isNullOrEmpty()) return@withContext Resource.Success(isEmpty = true)
            dao.deleteAll()
            dao.insert(calories.mapNotNull { it.toUiModel().toDatabaseModel() })
            Resource.Success(isEmpty = false)
        }
    }

}