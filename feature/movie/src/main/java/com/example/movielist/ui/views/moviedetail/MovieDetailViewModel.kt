package com.example.movielist.ui.views.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.example.movielist.data.model.Review
import com.example.movielist.data.model.Trailer
import com.example.movielist.data.remote.response.MovieDetailResponse
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.ui.base.BaseMovieDetailViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : BaseMovieDetailViewModel() {

    val movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieId = MutableLiveData<Int>()
    val trailerKey = MutableLiveData<Trailer>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            runCatching {
                showLoading()
                movieRepository.getMovieDetail(movieId)
            }.onSuccess {
                movieDetail.value = it
                hideLoading()
            }.onFailure {
                onError(it)
            }
        }
    }

    fun getTrailerDetail(movieId: Int){
        viewModelScope.launch {
            runCatching {
               movieRepository.getTrailerDetail(movieId)
            }.onSuccess {
                if (it != null) {
                    trailerKey.value = it.results?.get(0)
                }
            }.onFailure {
                onError(it)
            }
        }
    }

    override suspend fun loadReview(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Review> {
        val page = (loadParams?.key ?: firstPage).toString()

        return movieId.value?.let {
            movieRepository.getMovieReviews(page, it).let { reviewResponse ->
                reviewResponse.results?.toList()
            }
        } ?: listOf()
    }
}