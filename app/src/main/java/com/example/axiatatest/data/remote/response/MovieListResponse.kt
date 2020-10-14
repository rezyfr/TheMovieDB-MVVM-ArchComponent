package com.example.axiatatest.data.remote.response

import com.example.axiatatest.data.model.Movie

data class MovieListResponse(
    val page: Int? = null,
    val totalResults: Int? = null,
    val totalPages: Int? = null,
    var results: ArrayList<Movie>? = null
)