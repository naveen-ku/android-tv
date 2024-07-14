package com.example.stagetv.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail

class MoviePagingAdapter :
    PagingDataAdapter<ItemThumbnail, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {

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

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.im_movie_thumbnail)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // Bind View holder -> fint item and assign to xml elements
        val item = getItem(position)
        if (item != null) {
            val url = "https://image.tmdb.org/t/p/w500/" + item?.posterPath
            Glide.with(holder.itemImage.context).load(url).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(holder.itemImage!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)
        val params = view.layoutParams
        params.width = getWidthInPercent(parent?.context!!, 10)
        params.height = getHeightInPercent(parent?.context!!, 25)
        return MovieViewHolder(view)
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightInPercent(context: Context, percent: Int): Int {
        val height = context.resources.displayMetrics.heightPixels ?: 0
        return (height * percent) / 100
    }
}