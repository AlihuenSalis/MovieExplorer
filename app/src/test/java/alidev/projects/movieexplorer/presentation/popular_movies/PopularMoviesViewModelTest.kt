package alidev.projects.movieexplorer.presentation.popular_movies

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.model.Movie
import alidev.projects.movieexplorer.domain.usecase.GetPopularMoviesUseCase
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PopularMoviesViewModelTest {

    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPopularMoviesUseCase = mockk()
        viewModel = PopularMoviesViewModel(getPopularMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPopularMovies success, should update state with movies`() = runTest {
        // Given
        val movies = listOf(
            Movie(
                id = 1,
                title = "Title",
                overview = "Overview",
                posterPath = "poster.jpg",
                backdropPath = "backdrop.jpg",
                releaseDate = "2023",
                voteAverage = 8.0,
                voteCount = 100,
                popularity = 1.0,
                adult = false,
                genreIds = emptyList(),
            )
        )

        //  coEvery para funciones suspend
        coEvery { getPopularMoviesUseCase(any()) } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(movies))
        }

        // When & Then
        viewModel.state.test {
            // 1. Await the initial state
            val initialState = awaitItem()
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.movies).isEmpty()
            assertThat(initialState.error).isEmpty()

            // 2. Trigger the action
            viewModel.getPopularMovies()

            // Avanzar el dispatcher para procesar la corrutina
            advanceUntilIdle()

            // 3. Await the Loading state
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()

            // 4. Await the Success state
            val successState = awaitItem()
            assertThat(successState.isLoading).isFalse()
            assertThat(successState.movies).isEqualTo(movies)
            assertThat(successState.error).isEmpty()

            // 5. Cancela el test para evitar que siga esperando
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getPopularMovies error, should update state with error message`() = runTest {
        // Given
        val errorMessage = "An error occurred"

        // coEvery y flow para emitir secuencialmente
        coEvery { getPopularMoviesUseCase(any()) } returns flow {
            emit(Resource.Loading())
            emit(Resource.Error(errorMessage))
        }

        // When & Then
        viewModel.state.test {
            // 1. Await initial state
            val initialState = awaitItem()
            assertThat(initialState.isLoading).isFalse()
            assertThat(initialState.movies).isEmpty()
            assertThat(initialState.error).isEmpty()

            // 2. Trigger the action
            viewModel.getPopularMovies()

            // AVANZAR el dispatcher
            advanceUntilIdle()

            // 3. Await Loading state
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()

            // 4. Await Error state
            val errorState = awaitItem()
            assertThat(errorState.isLoading).isFalse()
            assertThat(errorState.movies).isEmpty()
            assertThat(errorState.error).isEqualTo(errorMessage)

            // 5. Cancelar el test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getPopularMovies should emit loading state immediately`() = runTest {
        // Given
        val movies = emptyList<Movie>()
        coEvery { getPopularMoviesUseCase(any()) } returns flow {
            emit(Resource.Loading())
            kotlinx.coroutines.delay(100) // delay pequeño para simular realidad
            emit(Resource.Success(movies))
        }

        viewModel.state.test {
            // Estado inicial
            awaitItem()

            // When
            viewModel.getPopularMovies()

            // Avanzar un poco pero no hasta el final
            advanceUntilIdle()

            // Then - Verificar que pasa por loading
            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()

            val successState = awaitItem()
            assertThat(successState.isLoading).isFalse()

            cancelAndIgnoreRemainingEvents()
        }
    }
}