package com.example.movieapp.service.model

import com.google.gson.annotations.SerializedName

class MovieDetailResponse(
    @SerializedName("Plot")
    var plot: String,

    @SerializedName("Director")
    var Director: String,
)
