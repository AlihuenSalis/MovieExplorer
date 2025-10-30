package alidev.projects.movieexplorer.presentation.detail_screen

import alidev.projects.movieexplorer.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)