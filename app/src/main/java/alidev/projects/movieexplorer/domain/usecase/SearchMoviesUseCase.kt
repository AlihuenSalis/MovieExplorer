package alidev.projects.movieexplorer.domain.usecase

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String, page: Int): Flow<Resource<List<Movie>>> {
        if (query.isBlank()) {
            return flowOf(Resource.Success(emptyList()))
        }
        return repository.searchMovies(query, page)
    }
}