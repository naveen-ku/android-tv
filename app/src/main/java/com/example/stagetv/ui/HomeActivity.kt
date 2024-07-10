package com.example.stagetv.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stagetv.databinding.ActivityHomeBinding
import com.example.stagetv.viewmodel.AuthViewModel
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
//        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.getPopularMovies()

    }
}