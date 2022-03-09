package com.example.spaceflightnews.spaceflightNewsAPI

import com.example.spaceflightnews.model.Article
import retrofit2.Call
import retrofit2.http.GET

interface ArticlesAPI {
    @GET("articles")
    fun getArticles(): Call<List<Article>>
}