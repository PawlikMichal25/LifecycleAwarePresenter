package io.baranmichal.thecaseagainstlivedata.movies.data

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String
)