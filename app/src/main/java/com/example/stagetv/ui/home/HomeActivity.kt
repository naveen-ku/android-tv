package com.example.stagetv.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.databinding.ActivityHomeBinding
import com.example.stagetv.ui.detail.DetailActivity
import com.example.stagetv.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, listFragment).commit()

        handleTrendingMoviesData(listFragment)
        handleTrendingTvSeriesData(listFragment)

//        handlePopularMoviesPaginatedData(listFragment)
//        handlePopularMoviesPaginatedDataUsingRecyclerView()


    }

//    private fun handlePopularMoviesPaginatedDataUsingRecyclerView() {
//        val recyclerView = binding.rvList
//        val adapter = MoviePagingAdapter()
//        recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter
//
//        homeViewModel.getPopularMoviesList().observe(this, Observer {
//            adapter.submitData(lifecycle, it)
//        })
//    }

//    private fun handlePopularMoviesPaginatedData(listFragment: ListFragment) {
//        homeViewModel.getPopularMoviesList().observe(this, Observer {
//            listFragment.bindPagingData("Popular Movies Paginated Data", it)
//        })
//    }

    private fun handleTrendingTvSeriesData(listFragment: ListFragment) {
        homeViewModel.getTrendingTvSeries()
        homeViewModel.trendingTvSeriesList.observe(this) { tvSeriesData ->
            if (tvSeriesData != null) {
                listFragment.bindTVData("Trending TV Series", tvSeriesData)
                listFragment.setOnContentSelectedListener {
                    updateBanner(it)
                }
                listFragment.setOnItemClickListener {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    intent.putExtra("mediaType", it.mediaType)
                    startActivity(intent)
                }
            }
        }
    }

    private fun handleTrendingMoviesData(listFragment: ListFragment) {
        homeViewModel.getTrendingMovies()
        homeViewModel.trendingMovieList.observe(this) { movieData ->
            if (movieData != null) {
                listFragment.bindMovieData("Trending Movies", movieData)
                listFragment.setOnContentSelectedListener {
                    updateBanner(it)
                }
                listFragment.setOnItemClickListener {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    intent.putExtra("mediaType", it.mediaType)
                    startActivity(intent)
                }
            }
        }
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
    }
}