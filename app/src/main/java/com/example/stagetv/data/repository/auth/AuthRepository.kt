package com.example.stagetv.data.repository.auth

import com.example.stagetv.data.network.auth.BaseAuthenticator
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authenticator: BaseAuthenticator) :
    BaseAuthRepository {

    override fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authenticator.getUser()
    }
}