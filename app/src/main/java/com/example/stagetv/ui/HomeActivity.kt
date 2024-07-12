package com.example.stagetv.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.databinding.ActivityHomeBinding
import com.example.stagetv.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()
    private lateinit var popularMoviesObserver: Observer<PagingData<ItemThumbnail>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        homeViewModel.getTrendingMovies()
//        homeViewModel.getMoviesList()
        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, listFragment).commit()

        homeViewModel.trendingMovieList.observe(this) {
            if (it != null) {
                Log.d("Ninja HomeActivity", "movieList: $it")
                listFragment.bindMovieData("Popular Movies",it)
            }
        }

        homeViewModel.getTrendingTvSeries()
        homeViewModel.trendingTvSeriesList.observe(this){
            if(it!= null){
                listFragment.bindTVData("Popular TV Series",it)
            }
        }

//        popularMoviesObserver = Observer{pagingData ->
//            listFragment.bindPagingData("Trending Movies", pagingData)
//        }
//
//        homeViewModel.getPopularMoviesList()
//        homeViewModel.popularMoviesList.observe(this, popularMoviesObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
//        homeViewModel.popularMoviesList.removeObserver(popularMoviesObserver)
    }
}