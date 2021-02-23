package com.example.movielist.ui.views.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.data.model.Movie
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class MovieListAdapter(
    private val itemClickListener: (Movie) -> Unit
) : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { (holder as MovieViewHolder).bindItem(it, itemClickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(data: Movie, listener: (Movie) -> Unit) {
            Glide.with(itemView)
                .load(data.getFullPosterPath().toString())
                .into(itemView.iv_poster)
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}