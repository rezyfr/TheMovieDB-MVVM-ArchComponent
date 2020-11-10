package com.example.movielist.data.remote.response

import com.example.movielist.data.model.Review

data class ReviewResponse(
    val id: Int? = null,
    val page: Int? = null,
    var results: List<Review>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)