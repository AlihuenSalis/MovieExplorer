package alidev.projects.movieexplorer.presentation.navigation

/**
 * Define las rutas de navegación de la aplicación
 */
object NavigationRoutes {
    const val HOME = "home"
    const val DETAIL = "detail/{movieId}"

    fun detailRoute(movieId: Int): String = "detail/$movieId"
}