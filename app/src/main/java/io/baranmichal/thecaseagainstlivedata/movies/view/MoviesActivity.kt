package io.baranmichal.thecaseagainstlivedata.movies.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import io.baranmichal.thecaseagainstlivedata.LifeApplication
import io.baranmichal.thecaseagainstlivedata.R
import io.baranmichal.thecaseagainstlivedata.base.presenter.PresenterProviders
import io.baranmichal.thecaseagainstlivedata.movies.data.Movie
import io.baranmichal.thecaseagainstlivedata.movies.di.MoviesModule
import io.baranmichal.thecaseagainstlivedata.movies.presenter.MoviesPresenter
import io.baranmichal.thecaseagainstlivedata.movies.presenter.MoviesPresenterFactory
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), MoviesView {

    @Inject
    lateinit var moviesPresenterFactory: MoviesPresenterFactory

    private lateinit var presenter: MoviesPresenter

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        injectDependencies()

        setupSwipeRefresh()
        setupRecyclerView()

        presenter = PresenterProviders.of(this, moviesPresenterFactory).get(MoviesPresenter::class.java)
        presenter.attachView(this, lifecycle)
        presenter.loadMovies()
    }

    private fun injectDependencies() {
        val app = application as LifeApplication
        app.getComponent()
            .plus(MoviesModule())
            .inject(this)
    }

    private fun setupSwipeRefresh() {
        swiperefresh_movies.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        swiperefresh_movies.setOnRefreshListener {
            presenter.refreshMovies()
        }
    }

    private fun setupRecyclerView() {
        recyclerview_movies.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerview_movies.adapter = adapter
    }

    override fun showLoading() {
        swiperefresh_movies.visibility = View.GONE
        textview_movies_error.visibility = View.GONE

        progressbar_movies.visibility = View.VISIBLE
    }

    override fun showRefresh() {
        swiperefresh_movies.isRefreshing = true
    }

    override fun showMovies(movies: List<Movie>) {
        progressbar_movies.visibility = View.GONE
        textview_movies_error.visibility = View.GONE

        swiperefresh_movies.visibility = View.VISIBLE
        swiperefresh_movies.isRefreshing = false
        adapter.updateMovies(movies)
    }

    override fun showLoadingError(message: String) {
        progressbar_movies.visibility = View.GONE
        swiperefresh_movies.visibility = View.GONE

        textview_movies_error.visibility = View.VISIBLE
        textview_movies_error.text = message
    }

    override fun showRefreshError(message: String) {
        swiperefresh_movies.isRefreshing = false

        Snackbar.make(layout_movies, message, Snackbar.LENGTH_SHORT).run {
            setAction(R.string.retry) { presenter.retryRefreshClicked() }
            show()
        }
    }
}
