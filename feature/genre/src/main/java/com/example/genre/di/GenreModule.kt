package com.example.genre.di

import com.example.genre.data.remote.GenreApi
import com.example.genre.data.repository.GenreRepository
import com.example.genre.data.repository.GenreRepositoryImpl
import com.example.genre.genrelist.GenreListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val genreModule = module {
    single<GenreRepository> {
        GenreRepositoryImpl(
            get(),
            get()
        )
    }
    viewModel { GenreListViewModel(get()) }
    factory { provideGenreApi(get()) }
}

fun provideGenreApi(retrofit: Retrofit): GenreApi =
    retrofit.create(GenreApi::class.java)