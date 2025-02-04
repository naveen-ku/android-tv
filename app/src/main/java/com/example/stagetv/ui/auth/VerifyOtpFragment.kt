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
import com.example.stagetv.R
import com.example.stagetv.data.network.NetworkResult
import com.example.stagetv.databinding.FragmentVerifyOtpBinding
import com.example.stagetv.ui.home.HomeActivity
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
                        is NetworkResult.Loading -> {
                            // Can show loader
                            showLoader()
                        }

                        is NetworkResult.Success -> {
                            Log.d(
                                "VerificationOtpFragment", "Verification success: ${resource.data}"
                            )
                            hideLoader()
                            startActivity(Intent(context, HomeActivity::class.java))
                            requireActivity().finish()
                        }

                        is NetworkResult.Failure -> {
                            Log.d(
                                "VerificationOtpFragment",
                                "Verification failed: ${resource.message}"
                            )
                            hideLoader()
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

    private fun showLoader() {
        binding.buttonVerifyOtp.isEnabled = false
        binding.pbVerifyLoading.visibility = View.VISIBLE
        binding.buttonVerifyOtp.text = ""
    }

    private fun hideLoader() {
        binding.buttonVerifyOtp.isEnabled = true
        binding.pbVerifyLoading.visibility = View.GONE
        binding.buttonVerifyOtp.text = getString(R.string.verify)
    }

}