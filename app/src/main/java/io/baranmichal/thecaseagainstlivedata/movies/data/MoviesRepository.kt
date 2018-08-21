package io.baranmichal.thecaseagainstlivedata.movies.data

import io.baranmichal.thecaseagainstlivedata.movies.data.network.MoviesClient
import io.baranmichal.thecaseagainstlivedata.testing.OpenForTesting
import io.reactivex.Single
import javax.inject.Inject

@OpenForTesting
class MoviesRepository @Inject constructor(
    private val moviesClient: MoviesClient
) {
    private var moviesCache: List<Movie>? = null

    fun loadMovies(): Single<List<Movie>> {
        val movies = moviesCache

        if (movies != null) {
            return Single.just(movies)
        } else {
            return moviesClient.getMovies().doOnSuccess { moviesCache = it }
        }
    }

    fun refreshMovies(): Single<List<Movie>> {
        return moviesClient.getMovies().doOnSuccess { moviesCache = it }
    }
}