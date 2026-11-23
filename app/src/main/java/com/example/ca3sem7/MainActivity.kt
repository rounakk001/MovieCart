package com.example.ca3sem7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()

        val repository = MovieRepository(
            database.movieDao()
        )

        val factory = MovieViewModelFactory(repository)

        setContent {

            val movieViewModel: MovieViewModel = viewModel(
                factory = factory
            )

            MovieScreen(viewModel = movieViewModel)
        }
    }
}
