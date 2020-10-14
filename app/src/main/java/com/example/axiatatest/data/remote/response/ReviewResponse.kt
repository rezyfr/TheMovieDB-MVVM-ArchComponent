package com.example.axiatatest.data.remote.response

import com.example.axiatatest.data.model.Review

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
)