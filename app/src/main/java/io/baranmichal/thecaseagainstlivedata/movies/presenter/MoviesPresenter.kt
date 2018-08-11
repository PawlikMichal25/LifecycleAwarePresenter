package io.baranmichal.thecaseagainstlivedata.movies.presenter

import io.baranmichal.thecaseagainstlivedata.LifeApplication
import io.baranmichal.thecaseagainstlivedata.base.presenter.BasePresenter
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulers
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import io.baranmichal.thecaseagainstlivedata.movies.di.MoviesModule
import io.baranmichal.thecaseagainstlivedata.movies.view.MoviesView
import java.io.IOException
import javax.inject.Inject

class MoviesPresenter : BasePresenter<MoviesView>() {

    @Inject
    lateinit var repository: MoviesRepository

    @Inject
    lateinit var schedulers: AppSchedulers

    @Inject
    lateinit var messageProvider: MoviesMessageProvider

    init {
        injectDependencies()
    }

    fun loadMovies() {
        repository.getMovies()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .doOnSubscribe {
                view()?.showLoading()
            }
            .subscribe({
                view()?.showMovies(it)
            }, {
                view()?.showError(getErrorMessage(it))
            })
            .autoClear()
    }

    private fun injectDependencies() {
        LifeApplication.get()
            .getComponent()
            .plus(MoviesModule())
            .inject(this)
    }

    private fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is IOException -> messageProvider.getServerErrorMessage()
            else -> messageProvider.getUnknownErrorMessage()
        }
    }
}