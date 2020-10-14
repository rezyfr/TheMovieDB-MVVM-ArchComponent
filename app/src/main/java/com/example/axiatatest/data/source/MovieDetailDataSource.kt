package com.example.axiatatest.data.source

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.data.model.Review
import com.example.axiatatest.ui.base.BaseMovieDetailViewModel
import com.example.axiatatest.ui.base.BaseMovieListViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class MovieDetailDataSource(
    private val viewModel: BaseMovieDetailViewModel
) : PageKeyedDataSource<Int, Review>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Review>
    ) {
        viewModel.viewModelScope.launch {
            try {
                viewModel.showLoading()
                val reviewList = loadDataSource(loadInitialParams = params)
                callback.onResult(
                    reviewList,
                    null,
                    // last page
                    if (reviewList.size < 10) null
                    // load next page
                    else viewModel.firstPage + 1
                )
                Timber.d("detailFirstPage = ${viewModel.firstPage}")
            } catch (e: Throwable) {
                viewModel.onError(e)
            } finally {
                viewModel.hideLoading()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Review>) {
        viewModel.viewModelScope.launch {
            try {
                val reviewList = loadDataSource(loadParams = params)
                callback.onResult(
                    reviewList,
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Review>) {
        if(viewModel.isLoading.value == true) return
        viewModel.viewModelScope.launch {
            try {
                viewModel.isLoadMore.value = true
                val reviewList = loadDataSource(loadParams = params)
                callback.onResult(
                    reviewList,
                    if(reviewList.size < 10) null
                    else params.key + 1
                )
                Timber.d("detailParamsKey = ${params.key}")
            } catch (e: Throwable){
                viewModel.onError(e)
            } finally {
                viewModel.hideLoading()
            }
        }
    }

    abstract suspend fun loadDataSource(
        loadInitialParams: LoadInitialParams<Int>? = null,
        loadParams: LoadParams<Int>? = null
    ): List<Review>
}
