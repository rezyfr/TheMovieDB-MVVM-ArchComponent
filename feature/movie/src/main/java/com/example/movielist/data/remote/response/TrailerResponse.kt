package com.example.movielist.data.remote.response

import com.example.movielist.data.model.Trailer

data class TrailerResponse(
    val id: Int? = null,
    val results: List<Trailer>? = null
)