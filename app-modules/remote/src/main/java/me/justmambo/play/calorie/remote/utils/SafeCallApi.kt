package me.justmambo.play.calorie.remote.utils

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import me.justmambo.play.calorie.remote.models.NetworkResult

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Wed 22 Feb 2023
 */

internal suspend inline fun <reified T> safeApiCall(block: () -> HttpResponse): NetworkResult<T> {
    return try {
        val response = block()
        return when (response.status) {
            HttpStatusCode.OK -> NetworkResult(
                isSuccessful = true,
                message = "Success",
                data = response.body() as T
            )
            HttpStatusCode.Unauthorized -> NetworkResult(message = "Unauthorized")
            else -> NetworkResult(message = "Failed Request")

        }
    } catch (e: Exception) {
        val message = e.message ?: "Error"
        NetworkResult(message = message)
    }
}