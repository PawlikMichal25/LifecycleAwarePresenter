package io.baranmichal.thecaseagainstlivedata.movies.di

import dagger.Subcomponent
import io.baranmichal.thecaseagainstlivedata.base.di.scopes.PerActivity
import io.baranmichal.thecaseagainstlivedata.movies.presenter.MoviesPresenter
import io.baranmichal.thecaseagainstlivedata.movies.view.MoviesActivity

@PerActivity
@Subcomponent(modules=[MoviesModule::class])
interface MoviesComponent{
    fun inject(activity: MoviesActivity)
}