package me.justmambo.play.calorie.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.justmambo.play.calorie.data.DefaultCalorieRepository
import me.justmambo.play.calorie.local.daos.CalorieDao
import me.justmambo.play.calorie.remote.CalorieApi

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    fun providesDefaultRepository(dao: CalorieDao, api: CalorieApi) =
        DefaultCalorieRepository(dao, api)

}