package com.maulana.kitabisa.movieslisttest.repository

import com.maulana.kitabisa.movieslisttest.model.MoviesResponse
import com.maulana.kitabisa.movieslisttest.network.ApiRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Maulana Rahmatullah
 * @describe Class repository hit API from network
 */
class ApiRepository {
    private val service = ApiRetrofit.create()

    suspend fun showListMovies(category: String, apiKey: String) = withContext(Dispatchers.IO) {
        service.showListMovies(category, apiKey)
    }
    suspend fun getDetailMovie(movieId: Int, apiKey: String) = withContext(Dispatchers.IO) {
        service.getDetailMovie(movieId, apiKey)
    }
    suspend fun showListReviews(movieId: Int, apiKey: String) = withContext(Dispatchers.IO) {
        service.showListReviews(movieId, apiKey)
    }
}