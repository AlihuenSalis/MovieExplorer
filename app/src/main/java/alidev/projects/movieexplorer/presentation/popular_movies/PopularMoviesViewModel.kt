package alidev.projects.movieexplorer.presentation.popular_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.usecase.GetPopularMoviesUseCase
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PopularMoviesState())
    val state: StateFlow<PopularMoviesState> = _state.asStateFlow()

    private var currentJob: Job? = null

    fun getPopularMovies() {
        // Prevenir múltiples llamadas simultáneas
        if (_state.value.isLoading || _state.value.isLoadingMore) return

        // Cancelar job anterior si existe
        currentJob?.cancel()

        currentJob = getPopularMoviesUseCase(_state.value.page).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = if (_state.value.page == 1) {
                        _state.value.copy(isLoading = true, error = "")
                    } else {
                        _state.value.copy(isLoadingMore = true, error = "")
                    }
                }
                is Resource.Success -> {
                    val newMovies = result.data ?: emptyList()

                    // Filtrar películas duplicadas por ID
                    val currentMovieIds = _state.value.movies.map { it.id }.toSet()
                    val uniqueNewMovies = newMovies.filter { it.id !in currentMovieIds }

                    _state.value = _state.value.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        movies = _state.value.movies + uniqueNewMovies,
                        page = _state.value.page + 1,
                        endReached = newMovies.isEmpty(),
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        error = result.message ?: "Error desconocido"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadNextPage() {
        if (!_state.value.endReached && !_state.value.isLoadingMore) {
            Log.d("Pagination", "Cargando página ${_state.value.page}")
            getPopularMovies()
        }
    }

    fun retry() {
        _state.value = PopularMoviesState()
        getPopularMovies()
    }

}


