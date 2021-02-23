package com.example.movielist.di

import com.example.movielist.data.remote.MovieApi
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.data.repository.MovieRepositoryImpl
import com.example.movielist.data.source.MovieDetailDataSource
import com.example.movielist.data.source.MovieListDataSource
import com.example.movielist.ui.views.moviedetail.MovieDetailViewModel
import com.example.movielist.ui.views.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val movieModule = module(override=true) {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { MovieListViewModel(get()) }
    factory { provideMovieApi(get()) }
    single { MovieListDataSource(get()) }
}

fun provideMovieDataSource(movieApi: MovieApi) = MovieListDataSource(movieApi)

fun provideMovieApi(retrofit: Retrofit): MovieApi =
    retrofit.create(MovieApi::class.java)