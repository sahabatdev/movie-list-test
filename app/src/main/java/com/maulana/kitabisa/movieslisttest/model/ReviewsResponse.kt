package com.maulana.kitabisa.movieslisttest.model

import com.google.gson.annotations.SerializedName

/**
 * @author Maulana Rahmatullah
 * @describe Data model Reviews
 */
data class ReviewsResponse(
    @SerializedName("results") val results:MutableList<ResultReview>
)