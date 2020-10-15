package com.example.axiatatest.data.remote.response

import com.example.axiatatest.BuildConfig
import com.example.axiatatest.data.model.Genre

data class MovieDetailResponse(
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val vote_count: Int,
    val backdrop_path: String
) {
    fun getBackdropPath() =
        if (backdrop_path.isBlank()) null else BuildConfig.BACKDROP_URL + backdrop_path
}