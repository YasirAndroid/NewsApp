package com.demo.newsdemo.network

import com.demo.newsdemo.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/v2/top-headlines")
    suspend fun getNewsData(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String): Response<NewsData>

}