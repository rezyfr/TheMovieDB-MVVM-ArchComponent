package com.example.movielist.ui.views.movielist

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.network.constants.ApiParams
import com.example.movielist.data.model.Movie
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.ui.base.BaseMovieListViewModel

class MovieListViewModel(
    private val movieRepository: MovieRepository
) : BaseMovieListViewModel() {

    val genreId = MutableLiveData<Int>()

    override suspend fun loadMovieList(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Movie> {
        val apiParams = HashMap<String, String>()
        apiParams[ApiParams.PAGE] = (loadParams?.key ?: firstPage).toString()
        apiParams[ApiParams.GENRE] = genreId.value.toString()

        return movieRepository.fetchMovieList(apiParams).results?.toList() ?: listOf()
    }
}