
# Análisis - Taller Rick And Morty

## 1. Objetivo
Desarrollar una aplicación Android utilizando Kotlin,
Jetpack Compose y Retrofit para consultar la API
de Rick And Morty.

## 2. Implementación
- Conexión con la API mediante Retrofit.
- Conversión de respuestas JSON.
- Modelo de datos Personaje.
- Repository para gestionar las peticiones.
- ViewModel para administrar los estados.
- Interfaz con lista y detalle de personajes.
- Navegación entre pantallas.

## 3. Manejo de errores
Se implementaron los estados Loading, Success y Error.

Se realizó una prueba sin conexión a Internet.
La aplicación presentó una excepción durante la
petición cuando no tenía protección.

Posteriormente se implementó el manejo de excepciones
en el Repository para evitar el cierre inesperado.

## 4. Resultados
- La lista de personajes carga correctamente.
- El detalle muestra la información del personaje.
- Sin conexión se muestra un mensaje de error.
- El botón Reintentar permite recuperar la información
  después de restablecer Internet.

## 5. Evidencias
- Se guarda el resultado de las evidencias en la carpeta "EVIDENCIAS" del menu principal.

## 6. Conclusión
Se comprendio la importancia del manejo de errores
en aplicaciones que consumen servicios web y la
separación de responsabilidades mediante Repository
y ViewModel.

