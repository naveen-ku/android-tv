package com.example.stagetv.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.marginStart
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail

class ItemPresenter() : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)
        val params = view.layoutParams
        params.width = getWidthInPercent(parent?.context!!, 10)
        params.height = getHeightInPercent(parent?.context!!, 25)
        return ViewHolder(view)
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightInPercent(context: Context, percent: Int): Int {
        val height = context.resources.displayMetrics.heightPixels ?: 0
        return (height * percent) / 100
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as? ItemThumbnail
        val rootView = viewHolder?.view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            rootView?.focusable = View.FOCUSABLE
        }
        val imageView = rootView?.findViewById<ImageView>(R.id.im_movie_thumbnail)
        val url = "https://image.tmdb.org/t/p/w500/" + content?.posterPath
        Glide.with(viewHolder?.view?.context!!).load(url).fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(imageView!!)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}