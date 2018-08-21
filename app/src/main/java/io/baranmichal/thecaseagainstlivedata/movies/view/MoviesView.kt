package io.baranmichal.thecaseagainstlivedata.movies.view

import io.baranmichal.thecaseagainstlivedata.movies.data.Movie

interface MoviesView {
    fun showLoading()
    fun showRefresh()

    fun showMovies(movies: List<Movie>)

    fun showLoadingError(message: String)
    fun showRefreshError(message: String)
}