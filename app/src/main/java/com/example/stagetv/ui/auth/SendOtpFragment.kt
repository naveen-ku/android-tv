package com.example.stagetv.ui.auth

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
import androidx.navigation.fragment.findNavController
import com.example.stagetv.data.network.Resource
import com.example.stagetv.databinding.FragmentSendOtpBinding
import com.example.stagetv.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SendOtpFragment : Fragment() {
    private lateinit var binding: FragmentSendOtpBinding
    private val authViewModel: AuthViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater, container, false)

        val text = "आप नए यूजर है?\n" +
                "अपना मोबाइल नंबर दर्ज करें\n\n"
        binding.tvBody1.text = text
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLogin.setOnClickListener {
                val cc = ccpMobile.selectedCountryCodeWithPlus
                val number = etMobile.text.toString().trim()
                val phoneNumber = "$cc$number"
                authViewModel.sendVerificationCode(phoneNumber, requireActivity())
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
                            Log.d("SendOtpFragment", "Verification initiated.")
                            val verificationId = authViewModel.verificationId.value
                            if (verificationId != null) {
                                Log.d("SendOtpFragment", "Verification ID is $verificationId.")
                                // Proceed to VerificationOtpFragment with the verification ID.
                                val action =
                                    SendOtpFragmentDirections.actionSendOtpFragmentToVerifyOtpFragment(
                                        verificationId
                                    )
                                findNavController().navigate(action)
                            } else {
                                // Handle error: Verification ID is null.
                                Log.d("SendOtpFragment", "Verification ID is null.")
                            }
                        }

                        is Resource.Failure -> {
                            Log.d(
                                "SendOtpFragment",
                                "Verification initiation failed: ${resource.message}"
                            )
                            // Show an error message
                            Toast.makeText(context, "Please try again, later!", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> Unit
                    }
                }

            }
        }
    }

}