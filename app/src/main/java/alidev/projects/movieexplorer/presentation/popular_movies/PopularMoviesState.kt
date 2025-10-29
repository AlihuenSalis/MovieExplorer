package alidev.projects.movieexplorer.presentation.popular_movies

import alidev.projects.movieexplorer.domain.model.Movie

data class PopularMoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val page: Int = 1,
    val endReached: Boolean = false,
    val isLoadingMore: Boolean = false
)
