
package com.example.rickandmortyapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PersonajesScreen(
    viewModel: PersonajesViewModel,
    onPersonajeClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rick & Morty",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.cargar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar personajes")
        }

        Spacer(modifier = Modifier.height(12.dp))

        when (val currentState = state) {

            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        currentState.items,
                        key = { it.id }
                    ) { personaje ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .clickable {
                                    onPersonajeClick(personaje.id)
                                }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = personaje.name,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text("Estado: ${personaje.status}")
                                Text("Especie: ${personaje.species}")
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(currentState.message)

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.cargar() }
                    ) {
                        Text("Reintentar")
                    }
                }
            }
        }
    }
}

@Composable
fun DetalleScreen(
    personajeId: Int,
    viewModel: PersonajesViewModel,
    onVolver: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val personaje = (state as? UiState.Success)
        ?.items
        ?.find { it.id == personajeId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Detalle del personaje",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (personaje != null) {
            Text(
                text = personaje.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text("ID: ${personaje.id}")
            Text("Estado: ${personaje.status}")
            Text("Especie: ${personaje.species}")
        } else {
            Text("Personaje no disponible. Regresa a la lista.")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}
