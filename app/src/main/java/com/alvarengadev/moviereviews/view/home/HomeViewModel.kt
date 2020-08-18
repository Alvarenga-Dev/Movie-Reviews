package com.alvarengadev.moviereviews.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarengadev.moviereviews.data.domain.Review
import com.alvarengadev.moviereviews.data.repository.ApiDataSourceRepository
import com.alvarengadev.moviereviews.data.repository.LocalDataSourceRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val localDataRepository: LocalDataSourceRepository
) : ViewModel() {

    val movieCriticsReviewData: MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val getMoviesCriticsReviewFavoritesData: MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val searchReviewData: MutableLiveData<String> = MutableLiveData()
    val isRemoveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getReviews() {
        val apiDataSourceRepository = ApiDataSourceRepository(movieCriticsReviewData)
        apiDataSourceRepository.callRetrofitApi()
    }

    fun removeReviewsFavorites(review: Review) {
        viewModelScope.launch {
            localDataRepository.removeFavorite(review)
            isRemoveData.value = true
        }
   }

    fun loadReviewsFavorites() {
        viewModelScope.launch {
            getMoviesCriticsReviewFavoritesData.value = localDataRepository.getReviews()
        }
    }

    fun addReviewFavorite(review: Review) {
        viewModelScope.launch {
            localDataRepository.addFavorite(review)
        }
    }

    suspend fun isFavorite(review: Review): Boolean {
        return localDataRepository.getReviews().contains(review)
    }

}