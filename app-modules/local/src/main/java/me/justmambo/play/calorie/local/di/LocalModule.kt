package me.justmambo.play.calorie.local.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.justmambo.play.calorie.local.CalorieDatabase
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
    fun providesDatabase(application: Application) =
        Room.databaseBuilder(
            context = application,
            klass = CalorieDatabase::class.java,
            name = CalorieDatabase.DATABASE_NAME
        )

    @Provides
    @Singleton
    fun providesCalorieDao(database: CalorieDatabase) = database.calorieDao()

}
