package alidev.projects.movieexplorer.presentation.search

import alidev.projects.movieexplorer.domain.model.Movie

data class SearchMoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val query: String = "",
    val page: Int = 1,
    val endReached: Boolean = false,
    val isLoadingMore: Boolean = false
)