package me.justmambo.play.calorie.local.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.justmambo.play.calorie.local.CalorieDatabase
import me.justmambo.play.calorie.local.daos.CalorieDao
import javax.inject.Singleton

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): CalorieDatabase =
        Room.databaseBuilder(
            context = context,
            klass = CalorieDatabase::class.java,
            name = CalorieDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun providesCalorieDao(database: CalorieDatabase): CalorieDao = database.calorieDao()

}
