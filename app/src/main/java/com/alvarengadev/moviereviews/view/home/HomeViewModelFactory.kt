package com.alvarengadev.moviereviews.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alvarengadev.moviereviews.data.repository.LocalDataSourceRepository

class HomeViewModelFactory(
    private val localDataRepository: LocalDataSourceRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(localDataRepository) as T
    }
}