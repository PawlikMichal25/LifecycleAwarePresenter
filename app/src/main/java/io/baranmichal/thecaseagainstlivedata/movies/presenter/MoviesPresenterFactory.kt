package io.baranmichal.thecaseagainstlivedata.movies.presenter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulers
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import javax.inject.Inject
import javax.inject.Provider

class MoviesPresenterFactory @Inject constructor(
    private val repository: Provider<MoviesRepository>,
    private val schedulers: Provider<AppSchedulers>,
    private val messageProvider: Provider<MoviesMessageProvider>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesPresenter(repository.get(), schedulers.get(), messageProvider.get()) as T
    }
}