package com.example.stagetv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory


@AssistedFactory
interface DetailViewModelAssistedFactory {
    fun create(id: Int, mediaType: String): DetailViewModel
}

class DetailViewModelFactoryImpl(
    private val assistedFactory: DetailViewModelAssistedFactory,
    private val id: Int,
    private val mediaType: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id, mediaType) as T
    }
}