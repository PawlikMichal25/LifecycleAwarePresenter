package io.baranmichal.thecaseagainstlivedata.movies.data.network

import io.baranmichal.thecaseagainstlivedata.movies.data.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface MoviesClient {

    @GET("movies")
    fun getMovies(): Single<List<Movie>>
}