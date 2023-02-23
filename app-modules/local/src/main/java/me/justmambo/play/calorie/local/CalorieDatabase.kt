package me.justmambo.play.calorie.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.justmambo.play.calorie.local.daos.CalorieDao
import me.justmambo.play.calorie.local.models.CalorieEntity

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Database(
    entities = [CalorieEntity::class],
    exportSchema = false,
    version = 7
)
abstract class CalorieDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao

    companion object {
        const val DATABASE_NAME = "calories_database"
    }

}