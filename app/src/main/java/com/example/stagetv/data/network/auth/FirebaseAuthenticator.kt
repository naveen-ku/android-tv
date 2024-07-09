package com.example.stagetv.data.network.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthenticator : BaseAuthenticator {
    override fun signOut(): FirebaseUser? {
        Firebase.auth.signOut()
        return Firebase.auth.currentUser
    }

    override fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}