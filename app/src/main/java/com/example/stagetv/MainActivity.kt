package com.example.stagetv

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = "क्या आप पहले से VIP मेंबर हैं ?\n" +
                "TV पर रजिस्टर करें\n\n" +
                "1. अपने फोन पर STAGE app खोले\n" +
                "2. ••• सेटिंग्स पर जाए\n" +
                "3. रजिस्टर टीवी पर क्लिक करे\n" +
                "4. 4 डिजिट कोड को एप्प में डाले\n"

        val leftText = findViewById<TextView>(R.id.tv_left_banner_text)
        leftText.text = text
    }
}