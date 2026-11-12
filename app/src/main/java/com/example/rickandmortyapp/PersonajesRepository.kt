
package com.example.rickandmortyapp

import kotlinx.coroutines.CancellationException

class PersonajesRepository(
    private val api: RickMortyApi
) {
    suspend fun obtenerPersonajes(): Result<PersonajesResponse> {
        return try {
            Result.success(api.obtenerPersonajes())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
