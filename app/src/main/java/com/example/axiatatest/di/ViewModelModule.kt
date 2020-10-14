package com.example.axiatatest.di

import com.example.axiatatest.ui.views.genrelist.GenreListViewModel
import com.example.axiatatest.ui.views.moviedetail.MovieDetailViewModel
import com.example.axiatatest.ui.views.movielist.MovieListViewModel
import com.example.axiatatest.ui.views.splashscreen.SplashscreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
    viewModel { GenreListViewModel(get()) }
    viewModel { SplashscreenViewModel() }
    viewModel { MovieDetailViewModel(get()) }
}