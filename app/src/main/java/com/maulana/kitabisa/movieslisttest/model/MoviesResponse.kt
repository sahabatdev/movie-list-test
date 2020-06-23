package com.maulana.kitabisa.movieslisttest.model

import com.google.gson.annotations.SerializedName

/**
 * @author Maulana Rahmatullah
 * @describe Data model response
 */
data class MoviesResponse(
    @SerializedName("results") val results:MutableList<Results>
)
