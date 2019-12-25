package com.bhavyakaria.finonews.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.network.ApiFactory
import com.bhavyakaria.finonews.ui.news_feeds.NewsFeedsFragment
import com.bhavyakaria.finonews.utils.extensions.addFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val bundle = Bundle()
        bundle.putParcelableArrayList("newsFeedsList", viewModel.newsList)

        val fragment : NewsFeedsFragment = NewsFeedsFragment.newInstance()
        fragment.arguments = bundle

        if (savedInstanceState == null) {
            addFragment(fragment, R.id.news_feeds_fragment)
        }

    }

}
