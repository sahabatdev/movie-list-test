package com.maulana.kitabisa.movieslisttest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import com.maulana.kitabisa.movieslisttest.repository.RoomRepository

/**
 * @author Maulana Rahmatullah
 * @describe class viewmodel for Favorit Activity
 */
class FavoritViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository = RoomRepository(application)

    fun getAllFavorit() : LiveData<MutableList<FavoritTable>> = liveData {
        val data = roomRepository.showListFavorit()
        emit(data)
    }
}