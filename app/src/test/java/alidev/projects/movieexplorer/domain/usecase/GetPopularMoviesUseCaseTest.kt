package alidev.projects.movieexplorer.domain.usecase

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.repository.MovieRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = mockk()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `invoke should call repository and return its result`() = runBlocking {
        // Given
        val fakeMovies =
            listOf(
                Movie(
                    1,
                    "Title",
                    "Overview",
                    "poster.jpg",
                    "backdrop.jpg",
                    "2023",
                    8.0,
                    100,
                    2.0,
                    false,
                    emptyList()
                )
            )
        val fakeFlow = flowOf(Resource.Success(fakeMovies))
        coEvery { movieRepository.getPopularMovies(any()) } returns fakeFlow

        // When
        val result = getPopularMoviesUseCase(1).first()

        // Then
        verify { movieRepository.getPopularMovies(1) }
        assertThat(result).isEqualTo(fakeFlow.first())
        assertThat(result.data).isEqualTo(fakeMovies)
    }
}
