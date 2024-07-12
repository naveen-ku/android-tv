package com.example.stagetv.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.stagetv.R
import com.example.stagetv.databinding.ActivityHomeBinding
import com.example.stagetv.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

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

        homeViewModel.movieList.observe(this) {
            if (it != null) {
                Log.d("Ninja HomeActivity", "movieList: $it")
                listFragment?.bindData(it)
            }
        }


    }
}