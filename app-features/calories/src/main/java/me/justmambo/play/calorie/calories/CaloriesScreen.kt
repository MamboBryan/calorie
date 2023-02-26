package me.justmambo.play.calorie.calories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriesScreenContent(
    navController: NavController,
//    viewModel: CaloriesViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Calories")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "search calories"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            Snackbar(modifier = Modifier.padding(8.dp)) {
                Text(text = "SnackBar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            Column(modifier = Modifier.weight(1f)) {

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