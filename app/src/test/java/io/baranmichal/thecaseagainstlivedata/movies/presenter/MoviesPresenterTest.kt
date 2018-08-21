package io.baranmichal.thecaseagainstlivedata.movies.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import android.arch.lifecycle.Lifecycle
import io.baranmichal.thecaseagainstlivedata.base.rx.AppSchedulersTest
import io.baranmichal.thecaseagainstlivedata.movies.data.Movie
import io.baranmichal.thecaseagainstlivedata.movies.data.MoviesRepository
import io.baranmichal.thecaseagainstlivedata.movies.view.MoviesView
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class MoviesPresenterTest {

    private val view: MoviesView = mock()
    private val lifecycle: Lifecycle = mock()
    private val repository: MoviesRepository = mock()
    private val messageProvider: MoviesMessageProvider = mock()
    private val schedulers = AppSchedulersTest.immediateSchedulers()

    private val presenter = MoviesPresenter(repository, schedulers, messageProvider)

    @Before
    fun before() {
        presenter.attachView(view, lifecycle)
    }

    @Test
    fun `when loadMovies, then loading shown`() {
        whenever(repository.loadMovies()).thenReturn(Single.just(emptyList()))

        presenter.loadMovies()

        verify(view).showLoading()
    }

    @Test
    fun `when loadMovies is successful, then movies are shown`() {
        val movies = arrayListOf(Movie(1, "title1"), Movie(2, "title2"))
        whenever(repository.loadMovies()).thenReturn(Single.just(movies))

        presenter.loadMovies()

        verify(view).showMovies(movies)
    }

    @Test
    fun `when during loadMovies IO error thrown, then connection message for loading error shown`() {
        val message = "Connection error"
        whenever(messageProvider.getConnectionErrorMessage()).thenReturn(message)
        whenever(repository.loadMovies()).thenReturn(Single.error(IOException()))

        presenter.loadMovies()

        verify(view).showLoadingError(message)
    }

    @Test
    fun `when during loadMovies unknown error thrown, then unknown message for loading error shown`() {
        val message = "Unknown error"
        whenever(messageProvider.getUnknownErrorMessage()).thenReturn(message)
        whenever(repository.loadMovies()).thenReturn(Single.error(ClassCastException()))

        presenter.loadMovies()

        verify(view).showLoadingError(message)
    }

    @Test
    fun `when refreshMovies is successful, then movies are shown`() {
        val movies = arrayListOf(Movie(1, "title1"), Movie(2, "title2"))
        whenever(repository.refreshMovies()).thenReturn(Single.just(movies))

        presenter.refreshMovies()

        verify(view).showMovies(movies)
    }

    @Test
    fun `when during refreshMovies IO error thrown, then connection message for refresh error shown`() {
        val message = "Connection error"
        whenever(messageProvider.getConnectionErrorMessage()).thenReturn(message)
        whenever(repository.refreshMovies()).thenReturn(Single.error(IOException()))

        presenter.refreshMovies()

        verify(view).showRefreshError(message)
    }

    @Test
    fun `when during refreshMovies unknown error thrown, then unknown message for refresh error shown`() {
        val message = "Unknown error"
        whenever(messageProvider.getUnknownErrorMessage()).thenReturn(message)
        whenever(repository.refreshMovies()).thenReturn(Single.error(ClassCastException()))

        presenter.refreshMovies()

        verify(view).showRefreshError(message)
    }

    @Test
    fun `when retryRefreshClicked, then refresh shown`() {
        whenever(repository.refreshMovies()).thenReturn(Single.just(emptyList()))

        presenter.retryRefreshClicked()

        verify(view).showRefresh()
    }
}