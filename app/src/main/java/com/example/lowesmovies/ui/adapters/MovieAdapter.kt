package com.example.lowesmovies.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesmovies.databinding.ListItemMovieBinding
import com.example.lowesmovies.models.Results


/**
 * Created by Neha Kushwah on 3/29/21.
 *
 */
class MovieAdapter(private val context: Context, private val list: ArrayList<Results>, private val listener: OnItemCardClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(
        private val context: Context,
        private val listItemMovieBinding: ListItemMovieBinding,
        private val listener: OnItemCardClickListener
    ) : RecyclerView.ViewHolder(listItemMovieBinding.root) {

        fun bind(movie: Results) {
            itemView.setOnClickListener {
                onItemClick(context, movie)
            }
            listItemMovieBinding.movie = movie
            listItemMovieBinding.executePendingBindings()
        }

        private fun onItemClick(context: Context, movie: Results) {
            movie?.let { listener.onClick(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            context,
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),listener
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemCardClickListener {
        fun onClick(movies: Results)
    }

    fun updateData(newList: List<Results>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}