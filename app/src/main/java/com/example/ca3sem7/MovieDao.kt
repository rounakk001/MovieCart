package com.example.ca3sem7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("UPDATE movies SET quantity = :quantity WHERE name = :name")
    suspend fun updateMovie(name: String, quantity: Int)

    @Query("DELETE FROM movies WHERE name = :name")
    suspend fun deleteMovie(name: String)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE name = :name LIMIT 1")
    suspend fun getMovieByName(name: String): Movie?
}
