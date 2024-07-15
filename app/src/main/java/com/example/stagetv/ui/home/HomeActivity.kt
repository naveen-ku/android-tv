package com.example.stagetv.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.stagetv.R
import com.example.stagetv.data.db.entity.ItemThumbnail
import com.example.stagetv.data.network.NetworkResult
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
        homeViewModel.isTvSeriesFetchingProgress.observe(this) { tvSeriesList ->
            when (tvSeriesList) {
                is NetworkResult.Loading -> {
                    // Show Progress bar
                }

                is NetworkResult.Success -> {
                    listFragment.bindTVData("Trending TV Series", tvSeriesList.data!!)
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

                is NetworkResult.Failure -> {
                    // Show Some error
                    Toast.makeText(this, "Some error in fetching tvSeries", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> Unit
            }

        }
    }

    private fun handleTrendingMoviesData(listFragment: ListFragment) {
        homeViewModel.getTrendingMovies()
        homeViewModel.isMovieFetchingProgress.observe(this) { moviesList ->
            when (moviesList) {
                is NetworkResult.Loading -> {
                    // Show some progress
                }

                is NetworkResult.Success -> {
                    listFragment.bindMovieData("Trending Movies", moviesList.data!!)
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

                is NetworkResult.Failure -> {
                    Toast.makeText(this, "Some error in fetching movies", Toast.LENGTH_SHORT).show()
                }

                else -> Unit
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