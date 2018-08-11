package io.baranmichal.thecaseagainstlivedata.base.data.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.baranmichal.thecaseagainstlivedata.base.di.scopes.PerApp
import io.baranmichal.thecaseagainstlivedata.movies.data.network.MoviesClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @PerApp
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @PerApp
    fun provideGson(): Gson = Gson()

    @Provides
    @PerApp
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @PerApp
    fun provideMoviesClient(retrofit: Retrofit): MoviesClient = retrofit.create(MoviesClient::class.java)

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/BaranMichal25/TheCaseAgainstLiveData/master/api/"
    }
}
