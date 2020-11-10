package com.example.di

import com.example.movielist.ui.views.moviedetail.MovieDetailViewModel
import com.example.ui.views.splashscreen.SplashscreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashscreenModule = module {
    viewModel { SplashscreenViewModel() }
}