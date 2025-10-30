package alidev.projects.movieexplorer.domain.usecase

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.MovieDetail
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUC @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> {
        return repository.getMovieDetail(movieId)
    }
}