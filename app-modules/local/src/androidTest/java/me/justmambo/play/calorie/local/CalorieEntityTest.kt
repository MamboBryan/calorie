package me.justmambo.play.calorie.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.justmambo.play.calorie.local.daos.CalorieDao
import me.justmambo.play.calorie.local.models.CalorieEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertTrue

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
@RunWith(AndroidJUnit4::class)
class CalorieEntityTest {

    private lateinit var dao: CalorieDao
    private lateinit var database: CalorieDatabase

    companion object {
        private fun getEntity(name: String, calories: Double = 10.0) = CalorieEntity(
            name = name,
            calories = calories,
            servingSizeGrams = 10.0,
            fatTotalGrams = 10.0,
            fatSaturatedGrams = 10.0,
            proteinGrams = 10.0,
            sodiumMilligrams = 10.0,
            potassiumMilligrams = 10.0,
            carbohydratesTotalGrams = 10.0,
            cholesterolMilligrams = 10.0,
            fiberGrams = 10.0,
            sugarGrams = 10.0
        )
    }

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CalorieDatabase::class.java).build()
        dao = database.calorieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(IOException::class)
    fun givenCalorieItemItSavesAndRetriesItSuccessfully() = runBlocking {
        val calorie = getEntity(name = "rice")
        dao.insert(item = calorie)
        val calories = dao.getCalorie(name = "rice")
        val first = calories.first()
        assertTrue { first == calorie }
    }

    @Test
    @Throws(IOException::class)
    fun givenTwoCalorieItemsWithTheSameNameItUpdatesItSuccessfully() = runBlocking {
        val calorie = getEntity(name = "rice")
        val updatedCalorie = getEntity(name = "rice", calories = 100.0)
        dao.insert(item = calorie)
        dao.insert(item = updatedCalorie)
        val calories = dao.searchCalories("rice")
        val first = calories.first()
        assertTrue { first.calories == updatedCalorie.calories }
    }

    @Test
    @Throws(IOException::class)
    fun givenCalorieItemYouCanDeleteItSuccessfully() = runBlocking {
        val calorie = getEntity(name = "rice")
        dao.insert(item = calorie)
        dao.delete(item = calorie)
        val list = dao.searchCalories("rice")
        assertTrue { list.any { it.name == "rice" }.not() }
    }

    @Test
    @Throws(IOException::class)
    fun afterAddingCalorieItemsYouCanDeleteAllItemsInTheTable() = runBlocking {
        listOf("one", "two", "three").map { getEntity(it) }.forEach {
            dao.insert(it)
        }
        dao.deleteAll()
        val calories = dao.searchCalories().first()
        assertTrue { calories.isEmpty() }
    }

}