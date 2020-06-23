package com.maulana.kitabisa.movieslisttest.repository

import android.app.Application
import com.maulana.kitabisa.movieslisttest.config.AppDatabase
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Maulana Rahmatullah
 * @describe repository for database local
 */
class RoomRepository(application: Application) {
    private val favDao = AppDatabase.getDb(application)!!.favDao()

    suspend fun showListFavorit() = withContext(Dispatchers.IO){
        favDao.getAllFavorit()
    }
    suspend fun findFavorit(movieId: Int) = withContext(Dispatchers.IO){
        favDao.findById(movieId)
    }
    suspend fun saveFavorit(fav: FavoritTable) = withContext(Dispatchers.IO){
        favDao.insertFavorit(fav)
    }
    suspend fun deleteFavorit(movieId: Int) = withContext(Dispatchers.IO){
        favDao.deleteFavorit(movieId)
    }

}