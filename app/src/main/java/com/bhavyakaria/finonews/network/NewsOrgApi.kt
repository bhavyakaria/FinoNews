package com.bhavyakaria.finonews.network

import com.bhavyakaria.finonews.models.ArticleResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsOrgApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category : String,
        @Query("country") country : String
    ): Response<ArticleResponseWrapper>
}