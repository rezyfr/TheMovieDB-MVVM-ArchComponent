package com.example.genre.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String? = null
) : Parcelable