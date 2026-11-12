package com.example.rickandmortyapp

data class Personaje(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String? = null
)

data class PersonajesResponse(
    val results: List<Personaje>
)
