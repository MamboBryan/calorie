package me.justmambo.play.calorie.calories

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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CaloriesViewModelTest {

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
    fun caloriesViewModel_OnInitialization_SnackbarErrorIsNull() = runTest {
        val viewModel = CaloriesViewModel(repository = FakeCalorieRepository())
        assertTrue {
            viewModel.hasError.first() == null
        }
    }

    @Test
    fun caloriesViewModel_OnInitialization_DoesntShowLoading() = runTest {
        val viewModel = CaloriesViewModel(repository = FakeCalorieRepository())
        assertTrue {
            !viewModel.isLoading.first()
        }
    }

    @Test
    fun caloriesViewModel_OnSearchReturningError_UiStateIsError() = runTest {

        val repository = FakeCalorieRepository()
        repository.showError()

        val viewModel = CaloriesViewModel(repository)
        viewModel.search()
        viewModel.searchJob?.join()

        val expected = CaloriesUiState.Error("request is unsuccessful")
        assertEquals(expected, viewModel.state.value)

    }

    @Test
    fun caloriesViewModel_OnSearchReturningEmpty_UiStateIsEmpty() = runTest {

        val repository = FakeCalorieRepository()
        repository.showEmpty()

        val viewModel = CaloriesViewModel(repository)
        viewModel.search()
        viewModel.searchJob?.join()

        assertEquals(CaloriesUiState.Empty, viewModel.state.value)

    }

    @Test
    fun caloriesViewModel_OnSearchReturningSuccess_UiStateIsSuccess() = runTest {

        val viewModel = CaloriesViewModel(FakeCalorieRepository())
        viewModel.search()
        viewModel.searchJob?.join()

        assertEquals(CaloriesUiState.Success, viewModel.state.value)

    }

    @Test
    fun caloriesViewModel_OnUpdateQuery_CorrectQueryString() = runTest {
        val viewModel = CaloriesViewModel(FakeCalorieRepository())
        val expected = "rice"
        viewModel.updateQuery(str = expected)
        assertEquals(expected, viewModel.query.first())
    }

    @Test
    fun caloriesViewModel_OnItemSearched_ListIsNotEmpty() = runTest {
        val viewModel = CaloriesViewModel(FakeCalorieRepository())
        viewModel.updateQuery(str = "rice")
        viewModel.search()
        viewModel.searchJob?.join()
        assertTrue {
            viewModel.calories.first().any { it.name == "rice" }
        }
    }

}