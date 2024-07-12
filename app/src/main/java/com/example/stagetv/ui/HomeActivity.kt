package com.example.stagetv.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.databinding.ActivityHomeBinding
import com.example.stagetv.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()
//    private lateinit var popularMoviesObserver: Observer<PagingData<ItemThumbnail>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, listFragment).commit()

        homeViewModel.getTrendingMovies()
        homeViewModel.trendingMovieList.observe(this) { movieData ->
            if (movieData != null) {
                Log.d("Ninja HomeActivity", "movieList: $movieData")
                listFragment.bindMovieData("Popular Movies", movieData)
                listFragment.setOnContentSelectedListener {
                    updateBanner(it)
                }
            }
        }

        homeViewModel.getTrendingTvSeries()
        homeViewModel.trendingTvSeriesList.observe(this) { tvSeriesData ->
            if (tvSeriesData != null) {
                Log.d("Ninja HomeActivity", "movieList: $tvSeriesData")
                listFragment.bindTVData("Popular TV Series", tvSeriesData)
                listFragment.setOnContentSelectedListener {
                    updateBanner(it)
                }
                listFragment.setOnItemClickListener {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    startActivity(intent)
                }
            }
        }

//        popularMoviesObserver = Observer{pagingData ->
//            listFragment.bindPagingData("Trending Movies", pagingData)
//        }
//
//        homeViewModel.getPopularMoviesList()
//        homeViewModel.popularMoviesList.observe(this, popularMoviesObserver)

    }

    private fun updateBanner(itemThumbnail: ItemThumbnail) {


        binding.tvTitle.text = itemThumbnail.title ?: itemThumbnail.name
        binding.tvTagline.text = itemThumbnail.mediaType.uppercase()
        binding.tvOverview.text = itemThumbnail.overview
        val url = "https://image.tmdb.org/t/p/w500/" + itemThumbnail.backdropPath
        Glide.with(this).load(url).into(binding.ivImage)

    }

    override fun onDestroy() {
        super.onDestroy()
//        homeViewModel.popularMoviesList.removeObserver(popularMoviesObserver)
    }
}