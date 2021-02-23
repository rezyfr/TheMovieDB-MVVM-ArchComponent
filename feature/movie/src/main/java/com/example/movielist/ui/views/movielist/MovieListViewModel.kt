package com.example.movielist.ui.views.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.base.views.BaseViewModel
import com.example.network.constants.ApiParams
import com.example.movielist.data.model.Movie
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.data.source.MovieListDataSource
import com.example.movielist.ui.base.BaseMovieListViewModel
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(
    private val movieListDataSource: MovieListDataSource
) : BaseViewModel() {

    var genreId = MutableLiveData<Int>()

    fun getMovieList(): Flow<PagingData<Movie>> {
        movieListDataSource.genreId = genreId
        return Pager(PagingConfig(pageSize = 10), pagingSourceFactory = {movieListDataSource} ).flow.cachedIn(viewModelScope)
    }

}