package com.maulana.kitabisa.movieslisttest.network

import androidx.lifecycle.Observer
import com.maulana.kitabisa.movieslisttest.model.MoviesResponse
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.model.ReviewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Maulana Rahmatullah
 * @describe List interface method Rest API
 */
interface ApiService {
    @GET("{type}")
    suspend fun showListMovies(@Path("type") type: String, @Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String) : Response<Results>

    @GET("{movie_id}/reviews")
    suspend fun showListReviews(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String) : Response<ReviewsResponse>

}