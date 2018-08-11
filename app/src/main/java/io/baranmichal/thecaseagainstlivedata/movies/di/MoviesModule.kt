package io.baranmichal.thecaseagainstlivedata.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.baranmichal.thecaseagainstlivedata.base.di.qualifiers.ApplicationContext
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import io.baranmichal.thecaseagainstlivedata.movies.presenter.MoviesMessageProvider

@Module
class MoviesModule {

    @Provides
    fun provideMoviesMessageProvider(@ApplicationContext context: Context): MoviesMessageProvider {
        return MoviesMessageProvider(context)
    }

    @Provides
    fun provideMoviesRepository(): MoviesRepository {
        return MoviesRepository()
    }
}