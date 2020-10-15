package com.example.axiatatest.data.remote.response

import com.example.axiatatest.data.model.Trailer

data class TrailerResponse(
    val id: Int? = null,
    val results: List<Trailer>? = null
)