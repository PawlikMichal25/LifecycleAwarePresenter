package io.baranmichal.thecaseagainstlivedata.movies.view

import io.baranmichal.thecaseagainstlivedata.movies.data.Movie

interface MoviesView {
    fun showLoading()
    fun showMovies(movies: List<Movie>)
    fun showError(message: String)
}