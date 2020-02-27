package by.androidacademy.architecture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.R
import by.androidacademy.architecture.domain.model.Movie

class MoviesAdapter(private val clickListener: (itemPosition: Int) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun setRating(position: Int, rating: Float) {
        this.movies[position].rating = rating
        notifyItemChanged(position, rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position]) {
            clickListener(position)
        }
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when {
            payloads.isEmpty() -> holder.bind(movies[position]) {
                clickListener(position)
            }
            else -> holder.setRating(payloads[0] as Float)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}