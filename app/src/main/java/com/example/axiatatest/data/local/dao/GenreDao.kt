package com.example.axiatatest.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.axiatatest.data.model.Genre

@Dao
interface GenreDao : BaseDao<Genre> {
    @Query("SELECT * FROM genre ORDER BY name ASC")
    suspend fun getGenreList(): List<Genre>?
}