package com.alvarengadev.moviereviews.view.home.common

import com.alvarengadev.moviereviews.data.domain.Review

interface OnReviewClickListener {
    fun onItemClick(review: Review)
}