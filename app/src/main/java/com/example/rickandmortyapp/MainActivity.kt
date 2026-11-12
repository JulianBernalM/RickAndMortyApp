
package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyAppTheme {

                val navController = rememberNavController()
                val personajesViewModel: PersonajesViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "lista"
                ) {

                    composable("lista") {
                        PersonajesScreen(
                            viewModel = personajesViewModel,
                            onPersonajeClick = { id ->
                                navController.navigate("detalle/$id")
                            }
                        )
                    }

                    composable(
                        route = "detalle/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->

                        val id = backStackEntry.arguments?.getInt("id") ?: 0

                        DetalleScreen(
                            personajeId = id,
                            viewModel = personajesViewModel,
                            onVolver = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
