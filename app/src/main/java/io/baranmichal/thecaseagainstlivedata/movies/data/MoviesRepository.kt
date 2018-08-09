package io.baranmichal.thecaseagainstlivedata.movies.data

import io.reactivex.Single

class MoviesRepository {
    fun getMovies(): Single<List<Movie>> {
        return Single.just(
            listOf(
                Movie("title1"),
                Movie("title2"),
                Movie("title3")
            )
        )
    }
}