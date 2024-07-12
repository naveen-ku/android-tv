package com.example.stagetv.paging

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stagetv.data.db.entity.ItemThumbnail

class MoviePagingAdapter :
    PagingDataAdapter<ItemThumbnail, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Assign values by finding view id

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ItemThumbnail>() {
            override fun areItemsTheSame(
                oldItem: ItemThumbnail, newItem: ItemThumbnail
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ItemThumbnail, newItem: ItemThumbnail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // Bind View holder -> fint item and assign to xml elements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }
}