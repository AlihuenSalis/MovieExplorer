package alidev.projects.movieexplorer.presentation.navigation

import alidev.projects.movieexplorer.presentation.detail_screen.MovieDetailScreen
import alidev.projects.movieexplorer.presentation.popular_movies.PopularMoviesScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Configuración principal de navegación de la aplicación usando Navigation3
 * Temporalmente simplificado hasta que Navigation3 esté completamente disponible
 */
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.HOME,
        modifier = modifier
    ) {
        // Ruta HOME - Pantalla de películas populares
        composable(NavigationRoutes.HOME) {
            PopularMoviesScreen(
                onMovieClick = { movieId ->
                    navController.navigate(NavigationRoutes.detailRoute(movieId))
                }
            )
        }

        // Ruta DETAIL - Pantalla de detalle de película
        composable(
            route = NavigationRoutes.DETAIL,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(
                movieId = movieId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}