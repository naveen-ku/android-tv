package com.example.stagetv.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stagetv.databinding.ActivityDetailBinding
import com.example.stagetv.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailActivity : FragmentActivity() {
    lateinit var binding: ActivityDetailBinding
//    private val detailViewModel: DetailViewModel by viewModels<DetailViewModel>()

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.DetailViewModelFactory
    private val detailViewModel: DetailViewModel by viewModels {
        DetailViewModel.providesFactory(
            assistedFactory = detailViewModelFactory,
            id = intent.getIntExtra("id", 0),
            mediaType = intent.getStringExtra("mediaType")!!
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val id = intent.getIntExtra("id", 0)
        val mediaType = intent.getStringExtra("mediaType")

//        val factory = DetailVIewModelFactory(movieRepository,)
//        detailViewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        detailViewModel.movieDetails.observe(this) { movieDetails ->
            if (movieDetails != null) {
                binding.tvDetailTitle.text = movieDetails.title
                binding.tvDetailTagline.text = movieDetails.tagline
                binding.tvDetailOverview.text = movieDetails.overview
                val url = "https://image.tmdb.org/t/p/w500/" + movieDetails.backdropPath
                Glide.with(this).load(url).fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.ivDetailImage)
            }

        }

        detailViewModel.tvSeriesDetails.observe(this) { tvSeriesDetails ->
            if (tvSeriesDetails != null) {
                binding.tvDetailTitle.text = tvSeriesDetails.name
                binding.tvDetailTagline.text = tvSeriesDetails.tagline
                binding.tvDetailOverview.text = tvSeriesDetails.overview
                val url = "https://image.tmdb.org/t/p/w500/" + tvSeriesDetails.backdropPath
                Glide.with(this).load(url).fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.ivDetailImage)
            }

        }
    }
}