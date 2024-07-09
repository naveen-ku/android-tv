package com.example.stagetv.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.stagetv.R
import dagger.hilt.android.AndroidEntryPoint

class HomeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}