package com.example.stagetv.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.stagetv.data.db.entity.ItemThumbnail
//import com.example.stagetv.data.db.entity.movie.MovieThumbnail
import com.example.stagetv.data.network.MovieService

class MoviePagingSource(private val movieService: MovieService) :
    PagingSource<Int, ItemThumbnail>() {
    override fun getRefreshKey(state: PagingState<Int, ItemThumbnail>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemThumbnail> {
        return try {
            val position = params.key ?: 1
            val response = movieService.getPopularMoviesList("en-us", position)
            Log.d("Ninja load","response : $response")
            return LoadResult.Page(
                data = response.movieThumbnails,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}