package com.bhavyakaria.finonews.ui.main

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.network.ApiFactory
import kotlinx.coroutines.*

class MainActivityViewModel(private val respository: ArticleRepository): ViewModel() {

    var newsList : ArrayList<Article> = arrayListOf()

    fun fetchData() = launchDataLoad {
        respository.fetchArticlesFromNetwork()
    }

    /**
     * Helper function to call a data load function with a loading spinner, errors will trigger a
     * snackbar.
     *
     * By marking `block` as `suspend` this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the viewModelScope. Before calling the
     *              lambda the loading spinner will display, after completion or error the loading
     *              spinner will stop
     */
    private fun launchDataLoad(block: suspend () -> LiveData<ArrayList<Article>>): Unit{
        viewModelScope.launch {
            try {
                newsList = block()
            } catch (error: Throwable) {

            } finally {

            }
        }
    }

}