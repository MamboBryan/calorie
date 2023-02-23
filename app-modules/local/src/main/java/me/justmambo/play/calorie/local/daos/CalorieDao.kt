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
    fun getCalories(): Flow<List<CalorieEntity>>

    @Query("SELECT * FROM calories WHERE name LIKE '%' || :query || '%'")
    fun getCalories(query: String): Flow<List<CalorieEntity>>

}