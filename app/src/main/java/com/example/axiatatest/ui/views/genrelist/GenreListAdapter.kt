package com.example.axiatatest.ui.views.genrelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.axiatatest.R
import com.example.axiatatest.data.model.Genre
import kotlinx.android.synthetic.main.item_genre_layout.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class GenreAdapter(
    private val listItem: ArrayList<Genre>,
    private val listener: (Genre) -> Unit
) : RecyclerView.Adapter<GenreAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItem[position], listener)
    }

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(data: Genre, listener: (Genre) -> Unit) {
            itemView.tv_genre.text = data.name
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    fun setData(data: List<Genre>) {
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }

}