package com.example.stagetv.ui

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.stagetv.data.db.entity.movie.MoviesList
import com.example.stagetv.data.db.entity.tvseries.TvSeriesList

class ListFragment : RowsSupportFragment() {

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
    }

    fun bindMovieData (header: String,movieList: MoviesList){
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        movieList.movieThumbnails.forEach{ movieThumbnail ->
            arrayObjectAdapter.add(movieThumbnail)
        }
        val headerItem = HeaderItem(header)
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }
    fun bindTVData (header: String,tvSeriesList: TvSeriesList){
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        tvSeriesList.tvSeriesThumbnails.forEach{ tvSeriesThumbnail ->
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

}