package io.baranmichal.thecaseagainstlivedata.base.di

import dagger.Component
import io.baranmichal.thecaseagainstlivedata.LifeApplication
import io.baranmichal.thecaseagainstlivedata.base.data.network.NetworkModule
import io.baranmichal.thecaseagainstlivedata.base.di.scopes.PerApp
import io.baranmichal.thecaseagainstlivedata.movies.di.MoviesComponent
import io.baranmichal.thecaseagainstlivedata.movies.di.MoviesModule

@PerApp
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: LifeApplication)
    fun plus(moviesModule: MoviesModule): MoviesComponent
}