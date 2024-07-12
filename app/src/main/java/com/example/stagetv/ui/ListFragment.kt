package com.example.stagetv.ui

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.movie.MovieThumbnail
import com.example.stagetv.data.db.entity.movie.MoviesList

class ListFragment : RowsSupportFragment() {

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
    }

    fun bindData (movieList: MoviesList){
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        movieList.movieThumbnails.forEach{ movieThumbnail ->
            arrayObjectAdapter.add(movieThumbnail)
        }
        val headerItem = HeaderItem("Popular Movies")
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
        // Adapter implementation
    }

}