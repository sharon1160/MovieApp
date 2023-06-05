package com.example.movieapp.service.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    var search: List<Movie>,

    @SerializedName("totalResults")
    var totalResults: String,

    @SerializedName("Response")
    var response: String
)
