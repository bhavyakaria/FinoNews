package com.bhavyakaria.finonews.ui.news_feeds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import kotlinx.android.synthetic.main.layout_for_news_feeds.view.*

class NewsFeedsAdapter(val items : ArrayList<Article>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_for_news_feeds, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsHeadline.text = items[position].title
        holder.newsHeadlineImage.load(items[position].urlToImage)
    }
}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val newsHeadline = view.text_view_news_headline!!
    val newsHeadlineImage = view.img_view_headline_img!!
}