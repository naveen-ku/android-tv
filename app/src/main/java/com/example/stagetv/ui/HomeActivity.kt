package com.example.stagetv.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.stagetv.data.repository.auth.AuthRepository
import com.example.stagetv.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : FragmentActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}