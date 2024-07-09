package com.example.stagetv.utils

sealed class VerificationOTPValidation {
    data object Success : VerificationOTPValidation()
    data class Failed(val message: String) : VerificationOTPValidation()
}

data class OTPFieldsState(
    val otp: VerificationOTPValidation,
)