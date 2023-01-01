package com.example.apexify

import com.example.apexify.Model.News
import com.example.apexify.Model.Store
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiInterface {
    @GET("news?auth=19319beea4f9eb0f896dd0d5caa1793e")
    fun fetchNews():Call<News>

    @GET("store?auth=19319beea4f9eb0f896dd0d5caa1793e")
    fun fetchStore():Call<Store>
}