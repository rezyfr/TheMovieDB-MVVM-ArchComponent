package com.example.data.local.db

import androidx.room.Room
import com.example.movielist.data.dao.MovieDao
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDb() }
    single { provideMovieDao(get()) }
    single { provideGenreDao(get()) }
}

fun Scope.provideAppDb() =
    Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "MAIN_DB").build()

fun provideMovieDao(db: AppDatabase): MovieDao {
    return db.getMovieDao()
}

fun provideGenreDao(db: AppDatabase): com.example.genre.data.dao.GenreDao {
    return db.getGenreDao()
}