package me.justmambo.play.calorie.calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.justmambo.play.calorie.data.CalorieRepository
import me.justmambo.play.calorie.data.DefaultCalorieRepository
import me.justmambo.play.calorie.data.models.Calorie
import me.justmambo.play.calorie.data.models.Resource
import javax.inject.Inject

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CaloriesViewModel @Inject constructor(
    val repository: CalorieRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: Flow<String> get() = _query

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    private val _hasError = MutableStateFlow<String?>(null)
    val hasError: Flow<String?> get() = _hasError

    private val _isEmpty = MutableStateFlow<Boolean>(false)
    val isEmpty: Flow<Boolean> get() = _isEmpty

    private val _state: MutableStateFlow<CaloriesUiState> =
        MutableStateFlow(CaloriesUiState.Loading)
    val state: StateFlow<CaloriesUiState> get() = _state

    private val _calories = _query.flatMapLatest { repository.getCalories() }
    val calories: Flow<List<Calorie>> get() = _calories

    var searchJob: Job? = null
    private var hasAlreadyLoadedOnce: Boolean = false

    init {
        viewModelScope.launch {
            if (calories.first().isEmpty().not())
                updateState(CaloriesUiState.Success)
        }
    }

    fun updateQuery(str: String) {
        _query.value = str
        hideEmpty()
    }

    fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            showLoading()
            val resource = repository.searchCalories(query = _query.value)
            hideLoading()
            when (resource) {
                is Resource.Error -> showError(resource = resource)
                is Resource.Success -> showSuccess(resource = resource)
            }
        }
    }

    private fun showSuccess(resource: Resource.Success) {
        when {
            resource.isEmpty -> {
                if (hasAlreadyLoadedOnce)
                    _isEmpty.value = true
                else
                    updateState(CaloriesUiState.Empty)
            }
            else -> {
                updateState(CaloriesUiState.Success)
                hasAlreadyLoadedOnce = true
            }
        }
    }

    fun hideEmpty() {
        _isEmpty.value = false
    }

    private fun showError(resource: Resource.Error) {
        when (hasAlreadyLoadedOnce) {
            true -> _hasError.value = resource.message
            false -> updateState(CaloriesUiState.Error(resource.message))
        }
    }

    fun hideError() {
        _hasError.value = null
    }

    private fun showLoading() {
        when (hasAlreadyLoadedOnce) {
            true -> _isLoading.value = true
            false -> updateState(CaloriesUiState.Loading)
        }
    }

    private fun hideLoading() {
        if (hasAlreadyLoadedOnce)
            _isLoading.value = false
    }

    private fun updateState(updateState: CaloriesUiState) {
        _state.value = updateState
    }

}