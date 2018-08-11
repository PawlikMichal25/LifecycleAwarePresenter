package io.baranmichal.thecaseagainstlivedata.movies.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        injectDependencies()

        setupRecyclerView()

        val presenter = PresenterProviders.of(this, moviesPresenterFactory).get(MoviesPresenter::class.java)
        presenter.attachView(this, lifecycle)
        presenter.loadMovies()
    }

    override fun showLoading() {
        recyclerView_movies.visibility = View.GONE
        textView_movies_error.visibility = View.GONE

        progressBar_movies.visibility = View.VISIBLE
    }

    override fun showMovies(movies: List<Movie>) {
        progressBar_movies.visibility = View.GONE
        textView_movies_error.visibility = View.GONE

        recyclerView_movies.visibility = View.VISIBLE
        adapter.updateMovies(movies)
    }

    override fun showError(message: String) {
        progressBar_movies.visibility = View.GONE
        recyclerView_movies.visibility = View.GONE

        textView_movies_error.visibility = View.VISIBLE
        textView_movies_error.text = message
    }

    private fun injectDependencies() {
        LifeApplication.get()
            .getComponent()
            .plus(MoviesModule())
            .inject(this)
    }

    private fun setupRecyclerView() {
        recyclerView_movies.adapter = adapter
    }
}
