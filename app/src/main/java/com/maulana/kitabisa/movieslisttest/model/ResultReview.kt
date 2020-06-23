package com.maulana.kitabisa.movieslisttest.model

import com.google.gson.annotations.SerializedName

/**
 * @author Maulana Rahmatullah
 * @describe Data model result reviews
 */
data class ResultReview(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String
)
