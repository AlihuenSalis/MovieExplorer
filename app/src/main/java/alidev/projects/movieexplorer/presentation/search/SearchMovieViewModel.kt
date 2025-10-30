package alidev.projects.movieexplorer.presentation.search

import alidev.projects.movieexplorer.domain.common.Resource
import alidev.projects.movieexplorer.domain.usecase.SearchMoviesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchMoviesState())
    val state: StateFlow<SearchMoviesState> = _state.asStateFlow()

    private var currentJob: Job? = null

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _state.value = SearchMoviesState()
            return
        }

        // Reiniciar estado para nueva búsqueda
        _state.value = SearchMoviesState(query = query)
        performSearch()
    }

    private fun performSearch() {
        if (_state.value.isLoading || _state.value.isLoadingMore) return

        currentJob?.cancel()

        currentJob = searchMoviesUseCase(
            query = _state.value.query,
            page = _state.value.page
        ).onEach { result ->
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

                    // Filtrar duplicados
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
        if (!_state.value.endReached &&
            !_state.value.isLoadingMore &&
            _state.value.query.isNotBlank()) {
            performSearch()
        }
    }

    fun clearSearch() {
        currentJob?.cancel()
        _state.value = SearchMoviesState()
    }
}