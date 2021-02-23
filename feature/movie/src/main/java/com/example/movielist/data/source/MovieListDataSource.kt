package com.example.movielist.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import com.example.movielist.data.model.Movie
import com.example.movielist.data.remote.MovieApi
import com.example.network.constants.ApiParams
import retrofit2.HttpException
import java.io.IOException

class MovieListDataSource(
    private val movieApi: MovieApi
) : PagingSource<Int, Movie>() {

    private val firstPage = 1
    var genreId = MutableLiveData<Int>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: firstPage

            val apiParams = HashMap<String, String>()
            apiParams[ApiParams.PAGE] = position.toString()
            apiParams[ApiParams.GENRE] = genreId.value.toString()

            val movieList = movieApi.getDiscoverMovieAsync(
                apiParams
            )

            val data = movieList.results as List<Movie>
            LoadResult.Page(
                data,
                if(position == firstPage) null
                else position -1,
                // last page
                if (data.isEmpty()) null
                else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception : Exception) {
            LoadResult.Error(exception)
        }
    }
}

