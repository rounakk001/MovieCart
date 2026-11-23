package com.example.ca3sem7

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        viewModelScope.launch {
            _movies.value = repository.getAllMovies()
        }
    }

    fun insertMovie(name: String, quantity: Int) {
        viewModelScope.launch {
            repository.insertMovie(
                Movie(name = name, quantity = quantity)
            )

            getAllMovies()
        }
    }

    fun updateMovie(name: String, quantity: Int) {
        viewModelScope.launch {
            repository.updateMovie(name, quantity)
            getAllMovies()
        }
    }

    fun deleteMovie(name: String) {
        viewModelScope.launch {
            repository.deleteMovie(name)
            getAllMovies()
        }
    }
}
