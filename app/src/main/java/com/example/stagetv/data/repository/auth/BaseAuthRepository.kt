package com.example.stagetv.data.repository.auth

import com.google.firebase.auth.FirebaseUser

interface BaseAuthRepository {


    fun signOut(): FirebaseUser?

    fun getCurrentUser(): FirebaseUser?
}