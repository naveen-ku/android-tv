package com.example.stagetv.ui

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList

class ListFragment : RowsSupportFragment() {

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    private var itemSelectedListener: ((ItemThumbnail) -> Unit)? = null
    private var itemClickedListener: ((ItemThumbnail)->Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickListener()
    }

    fun bindMovieData(header: String, movieList: MoviesList) {
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        movieList.movieThumbnails.forEach { movieThumbnail ->
            arrayObjectAdapter.add(movieThumbnail)
        }
        val headerItem = HeaderItem(header)
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }

    fun bindTVData(header: String, tvSeriesList: TvSeriesList) {
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        tvSeriesList.tvSeriesThumbnails.forEach { tvSeriesThumbnail ->
            arrayObjectAdapter.add(tvSeriesThumbnail)
        }
        val headerItem = HeaderItem(header)
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }

//    fun bindPagingData(header: String, pagingData: PagingData<ItemThumbnail>){
//        lifecycleScope.launch {
//            pagingData.collectLatest { data ->
//                val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
//                data.forEach{ item }
//            }
//        }
//    }

    fun setOnContentSelectedListener(listener: (ItemThumbnail) -> Unit) {
        this.itemSelectedListener = listener
    }

    fun setOnItemClickListener(listener: (ItemThumbnail)-> Unit){
        this.itemClickedListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is ItemThumbnail) {
                itemSelectedListener?.invoke(item)
            }
        }
    }

    inner class ItemViewClickListener: OnItemViewClickedListener{
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is ItemThumbnail) {
                itemClickedListener?.invoke(item)
            }
        }

    }

}