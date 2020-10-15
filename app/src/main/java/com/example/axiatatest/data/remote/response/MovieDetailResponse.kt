package com.example.axiatatest.data.remote.response

import com.example.axiatatest.BuildConfig
import com.example.axiatatest.data.model.Genre

data class MovieDetailResponse(
    val budget: Int? = null ,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val release_date: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val backdrop_path: String? = null
) {
    fun getBackdropPath() =
        if (backdrop_path.isNullOrBlank()) null else BuildConfig.BACKDROP_URL + backdrop_path
}