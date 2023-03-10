package me.justmambo.play.calorie.local.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.justmambo.play.calorie.local.models.CalorieEntity

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@Dao
interface CalorieDao : BaseDao<CalorieEntity> {

    @Query("SELECT * FROM calories")
    fun searchCalories(): Flow<List<CalorieEntity>>

    @Query("SELECT * FROM calories WHERE name LIKE '%' || :query || '%'")
    fun searchCalories(vararg query: String): List<CalorieEntity>

    @Query("SELECT * FROM calories WHERE name = :name")
    fun getCalorie(name: String): Flow<CalorieEntity>

    @Query("DELETE FROM calories")
    fun deleteAll()

}