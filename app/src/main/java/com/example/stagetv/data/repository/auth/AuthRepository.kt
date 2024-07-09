package com.example.stagetv.data.repository.auth

import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.db.entity.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val appDatabase: AppDatabase
) {

    suspend fun signOut() {
        // delete the database user & sign out
        appDatabase.userDao().deleteUser()
        firebaseAuth.signOut()
    }

    // Create the database user
    suspend fun signIn(user: User) {
        appDatabase.userDao().upsertUser(user)
    }

    suspend fun getSingleUser(): User? {
        return appDatabase.userDao().getSingleUser()
    }

}