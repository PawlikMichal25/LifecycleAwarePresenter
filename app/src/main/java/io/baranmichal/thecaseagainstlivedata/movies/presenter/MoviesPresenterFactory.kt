package io.baranmichal.thecaseagainstlivedata.movies.presenter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulers
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import javax.inject.Inject

class MoviesPresenterFactory @Inject constructor(
    private val repository: MoviesRepository,
    private val schedulers: AppSchedulers,
    private val messageProvider: MoviesMessageProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesPresenter(repository, schedulers, messageProvider) as T
    }
}