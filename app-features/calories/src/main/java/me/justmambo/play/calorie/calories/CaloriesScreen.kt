package me.justmambo.play.calorie.calories

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.justmambo.play.calorie.data.models.Screens

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriesScreenContent(
    navController: NavController, viewModel: CaloriesViewModel = hiltViewModel()
) {

    val error by viewModel.hasError.collectAsState(initial = null)
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val isEmpty by viewModel.isEmpty.collectAsState(initial = false)

    val query by viewModel.query.collectAsState(initial = "")
    val uiState by viewModel.state.collectAsState()
    val list by viewModel.calories.collectAsState(initial = listOf())

    Scaffold(topBar = {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Calories", fontSize = MaterialTheme.typography.displaySmall.fontSize
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.background),
                singleLine = true,
                placeholder = { Text(text = "Search...") },
                value = query,
                onValueChange = { viewModel.updateQuery(it) },
                trailingIcon = {
                    IconButton(enabled = query.isNotBlank() or (uiState !is CaloriesUiState.Loading),
                        onClick = { viewModel.search() }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "search food"
                        )
                    }
                },
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.background,
                    cursorColor = MaterialTheme.colorScheme.primary,
                )
            )

        }

    }, snackbarHost = {
        if (error != null) Snackbar(modifier = Modifier.padding(8.dp)) {
            Text(text = error?.uppercase() ?: "")
            Spacer(modifier = Modifier)
            Button(onClick = { viewModel.hideError() }) {
                Text(text = "retry")
            }
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedVisibility(
                visible = isLoading,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            AnimatedVisibility(
                visible = isEmpty,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Couldn't find any item for $query",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Icon(
                        modifier = Modifier.clickable { viewModel.hideEmpty() },
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "remove empty error",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                when (uiState) {
                    is CaloriesUiState.Error -> {
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
                                text = (uiState as CaloriesUiState.Error).message
                            )

                            Button(onClick = { viewModel.search() }) {
                                Text(text = "retry")
                            }

                        }
                    }
                    CaloriesUiState.Empty -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            val painter = painterResource(id = R.drawable.empty)
                            Image(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(250.dp),
                                painter = painter,
                                contentDescription = "Image of Error"
                            )

                            Text(text = "Empty")

                        }
                    }
                    CaloriesUiState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    CaloriesUiState.Success -> {
                        LazyColumn {
                            items(items = list) {
                                CalorieItem(calorie = it) {
                                    navController.navigate(Screens.Calorie.route.plus("/${it.name}"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CaloriesScreen(navController: NavController) {
    CaloriesScreenContent(navController = navController)
}

@Preview
@Composable
fun CaloriesScreenPreview() {
    val context = LocalContext.current
    CaloriesScreen(navController = NavController(context = context))
}