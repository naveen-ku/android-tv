package com.example.stagetv.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stagetv.data.db.entity.User
import com.example.stagetv.data.network.Resource
import com.example.stagetv.data.repository.auth.AuthRepository
import com.example.stagetv.utils.OTPFieldsState
import com.example.stagetv.utils.VerificationOTPValidation
import com.example.stagetv.utils.validOTP
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository, private val auth: FirebaseAuth
) : ViewModel() {

    private val _verificationId = MutableStateFlow<String?>(null)
    val verificationId: StateFlow<String?>
        get() = _verificationId

    private val _isVerificationInProgress =
        MutableStateFlow<Resource<Boolean>>(Resource.Unspecified())
    val isVerificationInProgress: Flow<Resource<Boolean>>
        get() = _isVerificationInProgress

    private val _validation = Channel<OTPFieldsState>()
    val validation = _validation.receiveAsFlow()

    init {
        viewModelScope.launch {
//            authRepository.signOut()
            val user: User? = authRepository.getSingleUser()
            if (user != null && !user.uid.isNullOrBlank()) {
                _isVerificationInProgress.emit(Resource.AlreadySuccess())
            }
        }

    }

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        Log.d("Ninja AuthViewModel", "sendVerificationCode: $phoneNumber $activity")

        runBlocking {
            _isVerificationInProgress.emit(Resource.Loading())
        }
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(authCredential: PhoneAuthCredential) {
                Log.d("Ninja", "onVerificationCompleted() $authCredential")
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.d("Ninja", "onVerificationFailed() $exception")
            }

            override fun onCodeSent(
                verificationId: String, token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                _verificationId.value = verificationId
                _isVerificationInProgress.value =
                    Resource.Success(true, "onCodeSent: $verificationId")
                Log.d("Ninja onCodeSent", "onCodeSent(): $verificationId")
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity).setCallbacks(callbacks).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun signInWithVerificationCode(verificationId: String, codeSendVerification: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, codeSendVerification)
        signInWithPhoneAuthCredential(credential, codeSendVerification)
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential, codeSendVerification: String
    ) {
        Log.d(
            "Ninja signInWithPhoneAuthCredential", "codeSendVerification $codeSendVerification"
        )
        if (checkValidation(codeSendVerification)) {
            runBlocking {
                _isVerificationInProgress.emit(Resource.Loading())
            }
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "Ninja signInWithPhoneAuthCredential",
                        "signInWithCredential success: ${task.result} "
                    )
                    _isVerificationInProgress.value =
                        Resource.Success(false, "signInWithCredential success: ${task.result}")
                    // Get current firebase user & then store it to db
                    val user = User(
                        uid = auth.currentUser!!.uid.toString(),
                        phoneNumber = auth.currentUser!!.phoneNumber.toString()
                    )
                    viewModelScope.launch {
                        authRepository.signIn(user)
                    }
                    Log.d(
                        "Ninja signInWithPhoneAuthCredential",
                        "currentUser ${auth.currentUser?.uid} ${auth.currentUser?.phoneNumber}"
                    )
                } else {
                    Log.d(
                        "Ninja signInWithPhoneAuthCredential",
                        "signInWithCredential failure: ${task.exception} "
                    )
                    _isVerificationInProgress.value =
                        Resource.Failure(false, "signInWithCredential failure: ${task.exception}")

                }
            }
        } else {
            val otpFieldsState = OTPFieldsState(validOTP(codeSendVerification))
            viewModelScope.launch {
                _validation.send(otpFieldsState)
            }
        }
    }
}

private fun checkValidation(codeSendVerification: String): Boolean {
    val otpValidation = validOTP(codeSendVerification)
    return otpValidation is VerificationOTPValidation.Success
}