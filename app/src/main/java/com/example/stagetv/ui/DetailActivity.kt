package com.example.stagetv.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stagetv.databinding.ActivityDetailBinding
import com.example.stagetv.ui.player.PlaybackActivity
import com.example.stagetv.viewmodel.DetailViewModel
import com.example.stagetv.viewmodel.DetailViewModelAssistedFactory
import com.example.stagetv.viewmodel.DetailViewModelFactoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailActivity : FragmentActivity() {
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var detailViewModelAssistedFactory: DetailViewModelAssistedFactory

    private val detailViewModel: DetailViewModel by viewModels {
        DetailViewModelFactoryImpl(
            detailViewModelAssistedFactory,
            intent.getIntExtra("id", 0),
            intent.getStringExtra("mediaType")!!
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

        binding.tvDetailPlay.setOnClickListener {
            Log.d("Ninja", "play button clicked")
            val intent = Intent(this, PlaybackActivity::class.java)
            startActivity(intent)
        }
    }
}