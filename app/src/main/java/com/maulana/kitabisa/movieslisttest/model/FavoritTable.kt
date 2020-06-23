package com.maulana.kitabisa.movieslisttest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Maulana Rahmatullah
 * @describe Data model table favorit (room db)
 */
@Entity(tableName = "favorit")
data class FavoritTable (
    @PrimaryKey val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String
)