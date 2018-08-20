package io.baranmichal.thecaseagainstlivedata.movies.di

import dagger.Subcomponent
import io.baranmichal.thecaseagainstlivedata.movies.view.MoviesActivity

@Subcomponent(modules=[MoviesModule::class])
interface MoviesComponent{
    fun inject(activity: MoviesActivity)
}