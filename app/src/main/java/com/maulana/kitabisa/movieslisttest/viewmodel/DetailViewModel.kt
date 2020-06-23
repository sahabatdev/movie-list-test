package com.maulana.kitabisa.movieslisttest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import com.maulana.kitabisa.movieslisttest.model.ResultReview
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.repository.ApiRepository
import com.maulana.kitabisa.movieslisttest.repository.RoomRepository

/**
 * @author Maulana Rahmatullah
 * @describe class viewmodel for Detail Activity
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val apiRepository = ApiRepository()
    private val roomRepository = RoomRepository(application)

    fun getDetailMovie(movieId: Int, apiKey: String) : LiveData<Results> = liveData {
        val movieDetail = apiRepository.getDetailMovie(movieId, apiKey)
        if(movieDetail.isSuccessful)
            emit(movieDetail.body()!!)
    }

    fun getListReviews(movieId: Int, apiKey: String) : LiveData<MutableList<ResultReview>> = liveData {
        val reviewsList = apiRepository.showListReviews(movieId, apiKey)
        if(reviewsList.isSuccessful)
            emit(reviewsList.body()!!.results)
    }

    fun saveFavorit(fav: FavoritTable) : LiveData<Unit> = liveData {
        val saveFav = roomRepository.saveFavorit(fav)
        emit(saveFav)
    }

    fun deleteFavorit(id: Int) : LiveData<Unit> = liveData {
        val deleteFav = roomRepository.deleteFavorit(id)
        emit(deleteFav)
    }

    fun findFavorite(id: Int) : LiveData<FavoritTable> = liveData {
        val item = roomRepository.findFavorit(id)
        try {
            emit(item!!)
        }catch (e: NullPointerException){

        }
    }

}