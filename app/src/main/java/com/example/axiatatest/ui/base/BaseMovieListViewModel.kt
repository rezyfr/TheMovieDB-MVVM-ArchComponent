package com.example.axiatatest.ui.base

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.data.source.MovieListDataSource

abstract class BaseMovieListViewModel : BaseViewModel() {

    private val pagedListConfig: PagedList.Config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(10)
            .setPrefetchDistance(3)
            .build()
    }

    // item list
    val movieList: LiveData<PagedList<Movie>> by lazy {
        LivePagedListBuilder(
            object : DataSource.Factory<Int, Movie>() {
                override fun create(): DataSource<Int, Movie> {
                    return createDataSource()
                }
            }, pagedListConfig
        ).build()
    }

    // data source
    private var dataSource: MovieListDataSource? = null

    fun createDataSource(): MovieListDataSource {
        return object : MovieListDataSource(viewModel = this@BaseMovieListViewModel) {
            override suspend fun loadDataSource(
                loadInitialParams: LoadInitialParams<Int>?,
                loadParams: LoadParams<Int>?,
            ): List<Movie> {
                return loadMovieList(loadInitialParams, loadParams)
            }
        }.apply {
            dataSource = this
        }
    }

    abstract suspend fun loadMovieList(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Movie>
}