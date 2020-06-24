package com.maulana.kitabisa.movieslisttest.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import com.maulana.kitabisa.movieslisttest.model.ResultReview
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.repository.ApiRepository
import com.maulana.kitabisa.movieslisttest.repository.RoomRepository
import java.lang.Exception

/**
 * @author Maulana Rahmatullah
 * @describe class viewmodel for Detail Activity
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val apiRepository = ApiRepository()
    private val roomRepository = RoomRepository(application)
    private val context = application.applicationContext

    private fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun getDetailMovie(movieId: Int, apiKey: String) : LiveData<Results?> = liveData {
        try {
            val movieDetail = apiRepository.getDetailMovie(movieId, apiKey)
            if (movieDetail.isSuccessful)
                emit(movieDetail.body()!!)
            else {
                emit(null)
                showMessage(movieDetail.errorBody()!!.string())
            }
        }catch (e: Exception){
            emit(null)
            showMessage(e.message!!)
        }
    }

    fun getListReviews(movieId: Int, apiKey: String) : LiveData<MutableList<ResultReview>?> = liveData {
        try{
            val reviewsList = apiRepository.showListReviews(movieId, apiKey)
            if(reviewsList.isSuccessful)
                emit(reviewsList.body()!!.results)
            else {
                emit(null)
                showMessage(reviewsList.errorBody()!!.string())
            }
        }catch (e: Exception){
            emit(null)
            showMessage(e.message!!)
        }
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