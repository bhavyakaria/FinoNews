package com.bhavyakaria.finonews.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.network.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    var newsList : ArrayList<Article> = arrayListOf()

    init {
        fetchData()
    }

    private fun fetchData(): ArrayList<Article>  {
        val service = ApiFactory.newsOrgApi
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTopHeadlines("business", "in")
            Log.d("Parzival", "Response: "+response.body()?.totalResults)
            newsList = response.body()!!.articles as ArrayList<Article>
        }
        return newsList
    }

}