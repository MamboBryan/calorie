package me.justmambo.play.calorie.calories

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.justmambo.play.calorie.data.DefaultCalorieRepository
import javax.inject.Inject

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@HiltViewModel
class CaloriesViewModel @Inject constructor(repository: DefaultCalorieRepository) : ViewModel() {


}