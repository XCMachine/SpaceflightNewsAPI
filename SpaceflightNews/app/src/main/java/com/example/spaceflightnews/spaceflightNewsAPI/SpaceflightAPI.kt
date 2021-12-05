package com.example.spaceflightnews.spaceflightNewsAPI

import com.example.spaceflightnews.get.Article
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface SpaceflightAPI {
    @GET("articles")
    fun getArticles(): Call<List<Article>>
}