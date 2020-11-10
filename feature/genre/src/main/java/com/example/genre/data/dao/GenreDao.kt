package com.example.genre.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genre.data.model.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre ORDER BY name ASC")
    suspend fun getGenreList(): List<Genre>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Genre>)

}