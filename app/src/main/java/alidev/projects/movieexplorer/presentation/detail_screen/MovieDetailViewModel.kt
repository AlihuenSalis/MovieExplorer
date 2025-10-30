package alidev.projects.movieexplorer.presentation.detail_screen

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.usecase.GetMovieDetailUC
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUC,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    init {
        // Obtener movieId de los argumentos de navegación
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            getMovieDetail(movieId)
        }
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = result.data)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun retry(movieId: Int) {
        getMovieDetail(movieId)
    }
}