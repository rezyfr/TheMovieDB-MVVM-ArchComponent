package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielist.data.dao.MovieDao
import com.example.genre.data.dao.GenreDao
import com.example.genre.data.model.Genre
import com.example.movielist.data.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getGenreDao(): GenreDao
}