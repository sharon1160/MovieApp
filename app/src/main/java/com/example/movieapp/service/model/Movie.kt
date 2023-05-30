package com.example.movieapp.service.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Poster")
    var poster: String,

    @SerializedName("Title")
    var title: String,

    @SerializedName("Year")
    var year: String,
)