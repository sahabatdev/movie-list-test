package com.maulana.kitabisa.movieslisttest.model

import com.google.gson.annotations.SerializedName

/**
 * @author Maulana Rahmatullah
 * @describe Data model response
 */
data class Results (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String
)