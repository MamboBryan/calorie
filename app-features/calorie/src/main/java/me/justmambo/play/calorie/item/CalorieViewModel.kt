package me.justmambo.play.calorie.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.justmambo.play.calorie.data.CalorieRepository
import me.justmambo.play.calorie.data.models.Calorie
import javax.inject.Inject

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Mon 27 Feb 2023
 */
@HiltViewModel
class CalorieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val repository: CalorieRepository
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _uiState: MutableStateFlow<CalorieUiState> =
        MutableStateFlow(CalorieUiState.Loading)
    val uiState: StateFlow<CalorieUiState> get() = _uiState

    var fetchJob: Job? = null

    init {
        fetch()
    }

    fun fetch() {
        fetchJob?.cancel()
        _uiState.value = CalorieUiState.Loading
        fetchJob = viewModelScope.launch {
            val calorie: Calorie = repository.getCalorie(name = id).first()
            _uiState.value = CalorieUiState.Loaded(calorie = calorie)
        }
    }

}