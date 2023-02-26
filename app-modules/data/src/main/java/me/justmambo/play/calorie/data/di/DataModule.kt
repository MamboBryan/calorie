package me.justmambo.play.calorie.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.justmambo.play.calorie.data.CalorieRepository
import me.justmambo.play.calorie.data.DefaultCalorieRepository
import me.justmambo.play.calorie.local.daos.CalorieDao
import me.justmambo.play.calorie.remote.CalorieApi
import javax.inject.Singleton

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesDefaultRepository(dao: CalorieDao, api: CalorieApi): CalorieRepository =
        DefaultCalorieRepository(dao, api)

}