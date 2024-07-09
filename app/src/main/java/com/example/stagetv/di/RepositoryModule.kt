package com.example.stagetv.di

import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    @ActivityScoped
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, appDatabase: AppDatabase) =
        AuthRepository(firebaseAuth, appDatabase)
}