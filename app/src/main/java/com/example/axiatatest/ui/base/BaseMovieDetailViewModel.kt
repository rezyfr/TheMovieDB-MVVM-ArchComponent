package com.example.axiatatest.ui.base

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.axiatatest.data.model.Review
import com.example.axiatatest.data.source.MovieDetailDataSource
import timber.log.Timber

abstract class BaseMovieDetailViewModel : BaseViewModel() {

    private val pagedListConfig: PagedList.Config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(10)
            .setPrefetchDistance(3)
            .build()
    }

    val reviewList: LiveData<PagedList<Review>> by lazy {
        LivePagedListBuilder(
            object : DataSource.Factory<Int, Review>() {
                override fun create(): DataSource<Int, Review> {
                    return createDataSource()
                }
            }, pagedListConfig
        ).build()
    }

    private var dataSource: MovieDetailDataSource? = null

    fun createDataSource(): MovieDetailDataSource {
        return object : MovieDetailDataSource(viewModel = this@BaseMovieDetailViewModel) {
            override suspend fun loadDataSource(
                loadInitialParams: LoadInitialParams<Int>?,
                loadParams: LoadParams<Int>?,
            ): List<Review> {
                Timber.d("LoadReview lagi")
                return loadReview(loadInitialParams, loadParams)
            }
        }.apply {
            dataSource = this
        }
    }

    abstract suspend fun loadReview(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Review>
}