package me.justmambo.play.calorie.item

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Mon 27 Feb 2023
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CalorieTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun calorieViewModel_OnInitialization_UiStateIsLoading() = runTest {

        val savedStateHandle = SavedStateHandle()
        savedStateHandle["id"] = "name"

        val viewModel = CalorieViewModel(
            repository = FakeCalorieRepository(),
            savedStateHandle = savedStateHandle
        )

        assertTrue {
            viewModel.uiState.value is CalorieUiState.Loading
        }
    }

    @Test
    fun calorieViewModel_OnNullId_UiStateIsNull() = runTest {

        val savedStateHandle = SavedStateHandle()
        savedStateHandle["id"] = null

        var exception: Exception? = null

        try {
            val viewModel = CalorieViewModel(
                repository = FakeCalorieRepository(),
                savedStateHandle = savedStateHandle
            )
        } catch (e: Exception) {
            exception = e
        }

        assertTrue {
            exception is IllegalStateException
        }

    }

}