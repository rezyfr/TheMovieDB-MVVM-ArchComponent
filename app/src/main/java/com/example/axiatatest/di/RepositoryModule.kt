package com.example.axiatatest.di

import androidx.room.Room
import com.example.axiatatest.data.local.dao.GenreDao
import com.example.axiatatest.data.local.dao.MovieDao
import com.example.axiatatest.data.local.db.AppDatabase
import com.example.axiatatest.data.repository.GenreRepository
import com.example.axiatatest.data.repository.GenreRepositoryImpl
import com.example.axiatatest.data.repository.MovieRepository
import com.example.axiatatest.data.repository.MovieRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module

val repositoryModule = module {
    single { provideAppDb() }
    single { provideMovieDao(get()) }
    single { provideGenreDao(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }
}

fun Scope.provideAppDb() =
    Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "AXIATA_TEST_DB").build()

fun provideMovieDao(db: AppDatabase): MovieDao {
    return db.getMovieDao()
}

fun provideGenreDao(db: AppDatabase): GenreDao {
    return db.getGenreDao()
}