package com.example.stagetv.data.network.auth

import com.google.firebase.auth.FirebaseUser

interface BaseAuthenticator {
    fun signOut(): FirebaseUser?

    fun getUser(): FirebaseUser?
}