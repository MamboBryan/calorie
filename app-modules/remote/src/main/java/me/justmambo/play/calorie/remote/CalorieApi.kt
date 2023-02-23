package me.justmambo.play.calorie.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.justmambo.play.calorie.remote.models.ItemsDto
import me.justmambo.play.calorie.remote.models.NetworkResult
import me.justmambo.play.calorie.remote.utils.safeApiCall
import javax.inject.Inject

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Wed 22 Feb 2023
 */
class CalorieApi @Inject constructor(
    val client: HttpClient
) {

    private val baseUrl = "https://api.calorieninjas.com/v1/nutrition"

    suspend fun getCalories(query: String): NetworkResult<ItemsDto> = safeApiCall {
        val response = client.post(baseUrl) {
            url { parameters.append("query", query) }
        }
        response.body()
    }

}