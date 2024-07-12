package com.example.stagetv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.stagetv.R
import com.example.stagetv.databinding.ActivityDetailBinding
import com.example.stagetv.databinding.ActivityHomeBinding

class DetailActivity : FragmentActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}