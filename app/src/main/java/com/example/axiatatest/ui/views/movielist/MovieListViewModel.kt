package com.example.axiatatest.ui.views.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.axiatatest.data.constants.ApiParams
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.data.repository.MovieRepository
import com.example.axiatatest.data.source.MovieListDataSource
import com.example.axiatatest.ui.base.BaseMovieListViewModel
import com.example.axiatatest.ui.base.BaseViewModel

class MovieListViewModel(
    private val movieRepository: MovieRepository
) : BaseMovieListViewModel() {

    val genreId = MutableLiveData<Int>()

    override suspend fun loadData(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Movie> {
        val apiParams = HashMap<String, String>()
        apiParams[ApiParams.PAGE] = (loadParams?.key ?: firstPage).toString()
        apiParams[ApiParams.GENRE] = genreId.value.toString()

        return movieRepository.fetchMovieList(apiParams).results?.toList() ?: listOf()
    }
}