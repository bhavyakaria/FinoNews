package com.bhavyakaria.finonews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bhavyakaria.finonews.network.ApiFactory
import com.flurry.android.FlurryAgent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FlurryAgent.Builder()
            .withLogEnabled(true)
            .build(this, "G966VY5S98Q67VZDQRWM")

        val service = ApiFactory.newsOrgApi
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTopHeadlines("business", "in")
            Log.d("Parzival", "Response: "+response.body()?.totalResults)
        }

    }
}
