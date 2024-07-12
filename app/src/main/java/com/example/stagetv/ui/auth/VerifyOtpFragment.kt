package com.example.stagetv.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.stagetv.data.network.Resource
import com.example.stagetv.databinding.FragmentVerifyOtpBinding
import com.example.stagetv.ui.HomeActivity
import com.example.stagetv.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VerifyOtpFragment : Fragment() {

    private lateinit var binding: FragmentVerifyOtpBinding
    private val authViewModel by viewModels<AuthViewModel>()
    private val args: VerifyOtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerifyOtpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.buttonVerifyOtp.setOnClickListener {
                val smsCode = etOtpCode.text.toString()
                val verificationId = args.verification
                authViewModel.signInWithVerificationCode(verificationId, smsCode)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.isVerificationInProgress.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Can show loader
                        }

                        is Resource.Success -> {
                            Log.d(
                                "VerificationOtpFragment", "Verification success: ${resource.data}"
                            )
                            startActivity(Intent(context, HomeActivity::class.java))
                            requireActivity().finish()
                        }

                        is Resource.Failure -> {
                            Log.d(
                                "VerificationOtpFragment",
                                "Verification failed: ${resource.message}"
                            )
                            // Show an error message
                            Toast.makeText(context, "आपका ओटीपी गलत है", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}