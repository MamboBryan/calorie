package me.justmambo.play.calorie.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Mon 27 Feb 2023
 */

@ExperimentalMaterial3Api
@Composable
private fun CalorieScreenContent(
    navController: NavController, viewModel: CalorieViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack, contentDescription = "navigate back"
                )
            }
        }, title = {

        })
    }) {
        Column(modifier = Modifier.padding(it)) {
            when (uiState) {
                CalorieUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val painter = painterResource(id = R.drawable.error)
                        Image(
                            modifier = Modifier
                                .width(250.dp)
                                .height(250.dp),
                            painter = painter,
                            contentDescription = "man thinking"
                        )

                        Text(text = "Oops", fontSize = TextUnit(24f, TextUnitType.Sp))
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = "Couldn't load details"
                        )

                        Button(onClick = { viewModel.fetch() }) {
                            Text(text = "retry")
                        }

                    }
                }
                CalorieUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is CalorieUiState.Loaded -> {
                    val calorie = (uiState as CalorieUiState.Loaded).calorie

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                    ) {
                        
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = calorie.name.split(" ")
                                    .joinToString(" ") { s ->
                                        s.replaceFirstChar { char -> char.uppercase() }
                                    },
                                fontSize = MaterialTheme.typography.displaySmall.fontSize
                            )
                            Text(text = "${calorie.servingSizeGrams}g serving")
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CalorieDetail(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color.Red),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.LocalFireDepartment,
                                        contentDescription = "",
                                        tint = Color.Red
                                    )
                                },
                                title = "Calories",
                                value = "${calorie.calories}kcal"
                            )

                            CalorieDetail(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color.Blue),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.BreakfastDining,
                                        contentDescription = "",
                                        tint = Color.Blue
                                    )
                                },
                                title = "Carbs",
                                value = "${calorie.carbohydratesTotalGrams}g"
                            )

                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CalorieDetail(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color(0xFFDDE015)),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.SetMeal,
                                        contentDescription = "",
                                        tint = Color(0xFFDDE015)
                                    )
                                },
                                title = "Fat",
                                value = "${calorie.fatTotalGrams}g"
                            )

                            CalorieDetail(
                                modifier = Modifier.weight(1f),
                                borderStroke = BorderStroke(0.4.dp, Color(0xFF3DDC97)),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Egg,
                                        contentDescription = "",
                                        tint = Color(0xFF3DDC97)
                                    )
                                },
                                title = "Protein",
                                value = "${calorie.proteinGrams}g"
                            )

                        }
                    }

                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieScreen(navController: NavController) {
    CalorieScreenContent(navController = navController)
}