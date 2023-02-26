package me.justmambo.play.calorie.remote.di

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.justmambo.play.calorie.remote.CalorieApi
import me.justmambo.play.calorie.remote.interceptors.NetworkInterceptor
import timber.log.Timber
import javax.inject.Singleton

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesNetworkInterceptor(@ApplicationContext context: Context) =
        NetworkInterceptor(context)

    @Singleton
    @Provides
    fun providesHttpClient(networkInterceptor: NetworkInterceptor) = HttpClient(OkHttp) {

        engine {
            this.addInterceptor(networkInterceptor)
        }

        install(ContentNegotiation) {
            json(json = Json {
                encodeDefaults = false
            })
        }

        install(Logging) {
            level = LogLevel.BODY
            object: Logger {
                override fun log(message: String) {
                    Timber.i("LOGGER", "BODY -> \n$message")
                }
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("x-api-key", "8/MqBej61B6ALLuEf7cIWg==tJbmaSTQHGZd6wLJ")
        }

    }

    @Singleton
    @Provides
    fun providesCalorieApi(client: HttpClient) = CalorieApi(client)

}