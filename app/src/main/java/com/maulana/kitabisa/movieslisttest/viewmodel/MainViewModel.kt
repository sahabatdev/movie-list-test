package com.maulana.kitabisa.movieslisttest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.repository.ApiRepository

/**
 * @author Maulana Rahmatullah
 * @describe class viewmodel for Main Activity
 */
class MainViewModel(application: Application) : AndroidViewModel(application){
    private val apiRepository = ApiRepository()

    fun getMovies(category: String, apiKey: String) : LiveData<MutableList<Results>> = liveData {
        val moviesList = apiRepository.showListMovies(category,apiKey)
        if(moviesList.isSuccessful)
            emit(moviesList.body()!!.results)
    }
}