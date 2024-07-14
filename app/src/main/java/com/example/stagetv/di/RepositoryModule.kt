package com.example.stagetv.di

import android.content.Context
import com.example.stagetv.data.db.AppDatabase
import com.example.stagetv.data.network.MovieService
import com.example.stagetv.data.network.TvSeriesService
import com.example.stagetv.data.repository.auth.AuthRepository
import com.example.stagetv.data.repository.movie.MovieRepository
import com.example.stagetv.data.repository.tvseries.TvSeriesRepository
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

    @Provides
    @ActivityScoped
    fun provideMovieRepository(
        movieService: MovieService,
        appDatabase: AppDatabase,
        appContext: Context
    ) =
        MovieRepository(movieService, appDatabase, appContext)

    @Provides
    @ActivityScoped
    fun provideTvSeriesRepository(
        tvSeriesService: TvSeriesService,
        appDatabase: AppDatabase,
        appContext: Context
    ) = TvSeriesRepository(tvSeriesService, appDatabase, appContext)
}