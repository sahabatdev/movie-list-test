package com.maulana.kitabisa.movieslisttest.repository

import androidx.room.*
import com.maulana.kitabisa.movieslisttest.model.FavoritTable

/**
 * @author Maulana Rahmatullah
 * @describe Interface dao for table favorite
 */
@Dao
interface FavoritDao {
    @Query("SELECT * FROM favorit")
    fun getAllFavorit() : MutableList<FavoritTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorit(fav: FavoritTable)

    @Query("DELETE FROM favorit WHERE id=:movieId")
    fun deleteFavorit(movieId: Int)

    @Query("SELECT * FROM favorit WHERE id=:movieId")
    fun findById(movieId: Int) : FavoritTable?
}