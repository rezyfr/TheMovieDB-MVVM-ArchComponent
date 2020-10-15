package com.example.axiatatest.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.axiatatest.data.model.Movie

@Dao
interface MovieDao : BaseDao<Movie> {
    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<Movie>?

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun getMovieById(id: String): Movie?

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovieById(id: String)

    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<Movie>?

    @Query("SELECT * FROM movie WHERE movie.isFavorite = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
    suspend fun getFavorite(pageSize: Int, pageIndex: Int): List<Movie>?
}