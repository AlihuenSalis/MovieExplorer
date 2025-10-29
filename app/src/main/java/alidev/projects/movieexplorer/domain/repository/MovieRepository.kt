package alidev.projects.movieexplorer.domain.repository

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>

}
