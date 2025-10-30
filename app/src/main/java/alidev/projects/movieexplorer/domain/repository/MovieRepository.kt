package alidev.projects.movieexplorer.domain.repository

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
    fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>>

}
