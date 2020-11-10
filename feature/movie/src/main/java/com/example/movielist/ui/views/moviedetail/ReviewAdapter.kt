package com.example.movielist.ui.views.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.data.model.Review
import kotlinx.android.synthetic.main.item_review_layout.view.*

class ReviewAdapter :
    PagedListAdapter<Review, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { (holder as ReviewViewHolder).bindItem(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_review_layout, parent, false)
        return ReviewViewHolder(view)
    }

    inner class ReviewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(data: Review) {
            itemView.tv_author.text = data.author
            itemView.tv_review.text = data.content
        }
    }
}