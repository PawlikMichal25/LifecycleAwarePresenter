package io.baranmichal.thecaseagainstlivedata.movies.presenter

import io.baranmichal.thecaseagainstlivedata.base.presenter.BasePresenter
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulers
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import io.baranmichal.thecaseagainstlivedata.movies.view.MoviesView
import java.io.IOException
import javax.inject.Inject

class MoviesPresenter @Inject constructor(
    private val repository: MoviesRepository,
    private val schedulers: AppSchedulers,
    private val messageProvider: MoviesMessageProvider
) : BasePresenter<MoviesView>() {

    override fun onViewAttached(view: MoviesView) {
        loadMovies()
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

    private fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is IOException -> messageProvider.getServerErrorMessage()
            else -> messageProvider.getUnknownErrorMessage()
        }
    }
}