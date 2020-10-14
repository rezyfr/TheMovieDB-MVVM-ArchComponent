package com.example.axiatatest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.axiatatest.data.local.dao.GenreDao
import com.example.axiatatest.data.local.dao.MovieDao
import com.example.axiatatest.data.model.Genre
import com.example.axiatatest.data.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    abstract fun getGenreDao(): GenreDao
}