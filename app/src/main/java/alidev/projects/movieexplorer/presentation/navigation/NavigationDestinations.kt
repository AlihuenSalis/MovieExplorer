package alidev.projects.movieexplorer.presentation.navigation

/**
 * Gestiona los destinos de navegación disponibles
 */
object NavigationDestinations {

    fun getAllDestinations(): List<String> = listOf(
        NavigationRoutes.HOME,
        NavigationRoutes.DETAIL
    )
}