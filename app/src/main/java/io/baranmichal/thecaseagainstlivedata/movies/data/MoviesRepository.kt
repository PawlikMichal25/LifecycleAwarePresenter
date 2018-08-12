package io.baranmichal.thecaseagainstlivedata.movies.data

import io.baranmichal.thecaseagainstlivedata.movies.data.network.MoviesClient
import io.baranmichal.thecaseagainstlivedata.testing.OpenForTesting
import io.reactivex.Single
import javax.inject.Inject

@OpenForTesting
class MoviesRepository @Inject constructor(
    private val moviesClient: MoviesClient
) {

    fun getMovies(): Single<List<Movie>> {
        return moviesClient.getMovies()
    }
}