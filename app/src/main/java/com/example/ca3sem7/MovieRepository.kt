package com.example.ca3sem7

class MovieRepository(
    private val dao: MovieDao
) {

    suspend fun insertMovie(movie: Movie) {
        dao.insertMovie(movie)
    }

    suspend fun updateMovie(name: String, quantity: Int) {
        dao.updateMovie(name, quantity)
    }

    suspend fun deleteMovie(name: String) {
        dao.deleteMovie(name)
    }

    suspend fun getAllMovies(): List<Movie> {
        return dao.getAllMovies()
    }

    suspend fun getMovieByName(name: String): Movie? {
        return dao.getMovieByName(name)
    }
}
