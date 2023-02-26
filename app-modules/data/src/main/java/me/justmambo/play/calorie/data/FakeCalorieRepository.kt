package me.justmambo.play.calorie.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import me.justmambo.play.calorie.data.models.Calorie
import me.justmambo.play.calorie.data.models.Resource

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sat 25 Feb 2023
 */
class FakeCalorieRepository : CalorieRepository {

    private val calories = MutableStateFlow<MutableList<Calorie>>(mutableListOf())

    private var isSuccessful = true
    private var isEmpty = false

    override fun getCalories(): Flow<List<Calorie>> = calories

    override fun getCalorie(name: String): Flow<Calorie> =
        calories.mapNotNull { list -> list.first { calorie -> calorie.name == name } }

    fun showError() {
        isSuccessful = false
    }

    fun showEmpty() {
        isEmpty = true
    }

    private fun createCalorie(name: String, calories: Double = 10.0) = Calorie(
        name = name,
        calories = calories,
        servingSizeGrams = 10.0,
        fatTotalGrams = 10.0,
        fatSaturatedGrams = 10.0,
        proteinGrams = 10.0,
        sodiumMilligrams = 10.0,
        potassiumMilligrams = 10.0,
        carbohydratesTotalGrams = 10.0,
        cholesterolMilligrams = 10.0,
        fiberGrams = 10.0,
        sugarGrams = 10.0
    )

    override suspend fun searchCalories(query: String): Resource {
        if (isSuccessful.not()) return Resource.Error(message = "Request is unsuccessful")
        if (isEmpty) return Resource.Success(isEmpty)
        val currentList = calories.value
        currentList.apply {
            clear()
            add(createCalorie(name = query))
        }
        calories.value = currentList
        return Resource.Success(isEmpty = false)
    }
}