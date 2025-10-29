package alidev.projects.movieexplorer.data.repository

import alidev.projects.movieexplorer.data.remote.MovieApi
import alidev.projects.movieexplorer.data.remote.dto.toDomain
import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getPopularMovies(page)
            val movies = response.results.map { it.toDomain() }
            emit(Resource.Success(movies))
        } catch (_: HttpException) {
            emit(Resource.Error("An unexpected error occurred."))
        } catch (_: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
