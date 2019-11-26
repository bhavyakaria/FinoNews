package com.bhavyakaria.finonews.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.network.ApiFactory
import com.bhavyakaria.finonews.ui.news_feeds.NewsFeedsFragment
import com.bhavyakaria.finonews.utils.extensions.addFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = ApiFactory.newsOrgApi
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTopHeadlines("business", "in")
            Log.d("Parzival", "Response: "+response.body()?.totalResults)
            val newsList : ArrayList<Article> = response.body()!!.articles as ArrayList<Article>

            val bundle = Bundle()
            bundle.putParcelableArrayList("newsFeedsList", newsList)

            val fragment : NewsFeedsFragment = NewsFeedsFragment.newInstance()
            fragment.arguments = bundle

            if (savedInstanceState == null) {
                addFragment(fragment, R.id.news_feeds_fragment)
            }
        }

    }
}
