package com.bhavyakaria.finonews.network

import com.bhavyakaria.finonews.utils.newsOrgApiKey
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apiKey", newsOrgApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkhttpClient for building http request url
    private val apiClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(apiClient)
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsOrgApi : NewsOrgApi = retrofit().create(NewsOrgApi::class.java)

}