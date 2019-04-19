package ru.helper.tzone.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import ru.helper.tzone.network.model.News

interface ApiService {
    @GET("/v2/everything/")
    fun getNews(@Query("apiKey") apiKey: String, @Query("sources") credits: String): Call<News>
}