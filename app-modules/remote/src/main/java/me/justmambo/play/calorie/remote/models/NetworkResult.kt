package me.justmambo.play.calorie.remote.models

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Wed 22 Feb 2023
 */
data class NetworkResult<T>(
    val isSuccessful: Boolean = false,
    val data: T? = null,
    val message: String,
)