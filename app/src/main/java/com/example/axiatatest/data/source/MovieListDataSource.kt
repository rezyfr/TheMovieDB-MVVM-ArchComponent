package com.example.axiatatest.data.source

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.ui.base.BaseMovieListViewModel
import kotlinx.coroutines.launch

abstract class MovieListDataSource(
    private val viewModel: BaseMovieListViewModel
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        viewModel.viewModelScope.launch {
            try {
                viewModel.showLoading()
                val movieList = loadDataSource(loadInitialParams = params)
                callback.onResult(
                    movieList,
                    null,
                    // last page
                    if (movieList.size < 10) null
                    // load next page
                    else viewModel.firstPage + 1
                )
            } catch (e: Throwable) {
                viewModel.onError(e)
            } finally {
                viewModel.hideLoading()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        viewModel.viewModelScope.launch {
            try {
                val movieList = loadDataSource(loadParams = params)
                callback.onResult(
                    movieList,
                    if (params.key == viewModel.firstPage) null
                    else (params.key - 1)
                )
            } catch (e: Throwable) {
                viewModel.onError(e)
            } finally {
                viewModel.hideLoading()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        if (viewModel.isLoading.value == true) return
        viewModel.viewModelScope.launch {
            try {
                viewModel.isLoadMore.value = true
                val movieList = loadDataSource(loadParams = params)
                callback.onResult(
                    movieList,
                    if (movieList.size < 10) null
                    else (params.key + 1)
                )
            } catch (e: Throwable) {
                viewModel.onError(e)
            } finally {
                viewModel.hideLoading()
            }
        }
    }

    abstract suspend fun loadDataSource(
        loadInitialParams: LoadInitialParams<Int>? = null,
        loadParams: LoadParams<Int>? = null
    ): List<Movie>
}

