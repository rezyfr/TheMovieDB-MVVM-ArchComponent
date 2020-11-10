package com.example.movielist.data.remote.response

import com.example.movielist.data.model.Movie

data class MovieListResponse(
    val page: Int? = null,
    val totalResults: Int? = null,
    val totalPages: Int? = null,
    var results: ArrayList<Movie>? = null
)