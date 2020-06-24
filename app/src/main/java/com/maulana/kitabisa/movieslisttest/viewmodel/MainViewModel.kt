package com.maulana.kitabisa.movieslisttest.viewmodel

import android.app.Application
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.repository.ApiRepository
import com.maulana.kitabisa.movieslisttest.views.MainActivity
import okhttp3.internal.wait
import java.lang.Exception
import java.net.UnknownHostException

/**
 * @author Maulana Rahmatullah
 * @describe class viewmodel for Main Activity
 */
class MainViewModel(application: Application) : AndroidViewModel(application){
    private val apiRepository = ApiRepository()
    private val context = application.applicationContext

    private fun showMessage(message: String){
        Toast.makeText(context, message, LENGTH_LONG).show()
    }

    fun getMovies(category: String, apiKey: String) : LiveData<MutableList<Results>?> = liveData {
        try {
            val moviesList = apiRepository.showListMovies(category, apiKey)
            if (moviesList.isSuccessful)
                emit(moviesList.body()!!.results)
            else {
                emit(null)
                showMessage(moviesList.errorBody()!!.string())
            }
        }catch (e : Exception){
            emit(null)
            showMessage(e.message!!)
        }
    }
}