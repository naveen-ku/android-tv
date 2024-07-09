package com.example.stagetv.ui

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.stagetv.R
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragmentActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set login left banner
        val text = "क्या आप पहले से VIP मेंबर हैं ?\n" +
                "TV पर रजिस्टर करें\n\n" +
                "1. अपने फोन पर STAGE app खोले\n" +
                "2. ••• सेटिंग्स पर जाए\n" +
                "3. रजिस्टर टीवी पर क्लिक करे\n" +
                "4. 4 डिजिट कोड को एप्प में डाले\n"

        val leftText = findViewById<TextView>(R.id.tv_left_banner_text)
        leftText.text = text

        // Right pages
    }
}