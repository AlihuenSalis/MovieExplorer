package alidev.projects.movieexplorer.data.repository

import alidev.projects.movieexplorer.data.remote.MovieApi
import alidev.projects.movieexplorer.data.remote.dto.MovieDto
import alidev.projects.movieexplorer.data.remote.dto.MovieResponse
import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImplTest {

    private lateinit var repository: MovieRepository
    private lateinit var movieApi: MovieApi

    @Before
    fun setUp() {
        movieApi = mockk()
        repository = MovieRepositoryImpl(movieApi)
    }

    @Test
    fun `getPopularMovies success, should emit Loading and then Success with mapped data`() = runBlocking {
        // Given
        val movieDto = MovieDto(1, "Title", "Overview", "/path.jpg", "/backdrop.jpg", "2023-01-01", 7.5, 100, 99.9, false, emptyList())
        val movieResponse = MovieResponse(1, listOf(movieDto), 10, 200)
        coEvery { movieApi.getPopularMovies(any()) } returns movieResponse

        // When & Then
        repository.getPopularMovies(1).test {
            // First emission should be Loading
            val loading = awaitItem()
            assertThat(loading).isInstanceOf(Resource.Loading::class.java)

            // Second emission should be Success
            val success = awaitItem()
            assertThat(success).isInstanceOf(Resource.Success::class.java)
            assertThat(success.data).hasSize(1)
            assertThat(success.data?.get(0)?.id).isEqualTo(movieDto.id)
            assertThat(success.data?.get(0)?.title).isEqualTo(movieDto.title)

            // No more emissions
            awaitComplete()
        }
    }

    @Test
    fun `getPopularMovies network error, should emit Loading and then Error`() = runBlocking {
        // Given
        val errorMessage = "Network error"
        coEvery { movieApi.getPopularMovies(any()) } throws IOException(errorMessage)

        // When & Then
        repository.getPopularMovies(1).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)

            val error = awaitItem()
            assertThat(error).isInstanceOf(Resource.Error::class.java)
            assertThat(error.message).isEqualTo("Couldn't reach server. Check your internet connection.")

            awaitComplete()
        }
    }

    @Test
    fun `getPopularMovies http error, should emit Loading and then Error`() = runBlocking {
        // Given
        coEvery { movieApi.getPopularMovies(any()) } throws mockk<HttpException>()

        // When & Then
        repository.getPopularMovies(1).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)

            val error = awaitItem()
            assertThat(error).isInstanceOf(Resource.Error::class.java)
            assertThat(error.message).isEqualTo("An unexpected error occurred.")

            awaitComplete()
        }
    }
}
