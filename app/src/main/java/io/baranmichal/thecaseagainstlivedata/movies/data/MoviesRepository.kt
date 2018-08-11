package io.baranmichal.thecaseagainstlivedata.movies.data

import io.baranmichal.thecaseagainstlivedata.movies.data.network.MoviesClient
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesClient: MoviesClient
) {

    fun getMovies(): Single<List<Movie>> {
        return moviesClient.getMovies()
    }
}