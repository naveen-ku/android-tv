package com.example.stagetv.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stagetv.R
import com.example.stagetv.databinding.FragmentSendOtpBinding

class SendOtpFragment : Fragment() {
    private lateinit var binding:FragmentSendOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater,container,false)

        val text = "आप नए यूजर है?\n" +
                "अपना मोबाइल नंबर दर्ज करें\n\n"
        binding.tvBody1.text = text
        return binding.root
    }

}