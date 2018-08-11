package io.baranmichal.thecaseagainstlivedata.movies.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.baranmichal.thecaseagainstlivedata.R
import io.baranmichal.thecaseagainstlivedata.movies.data.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
    }

    fun updateMovies(movies: List<Movie>) {
        setMovies(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val titleView = item.textView_movie_title

        fun bind(position: Int) {
            val movie = movies[position]

            titleView.text = movie.title
        }
    }
}