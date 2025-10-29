package alidev.projects.movieexplorer.domain.usecase

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> {
        return repository.getPopularMovies(page)
    }
}
