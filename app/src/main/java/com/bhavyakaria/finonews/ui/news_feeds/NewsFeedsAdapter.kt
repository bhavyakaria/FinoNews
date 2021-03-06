package com.bhavyakaria.finonews.ui.news_feeds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.utils.extensions.parseServerDate
import com.bhavyakaria.finonews.utils.extensions.removeSourceFromHeadline
import kotlinx.android.synthetic.main.layout_for_news_feeds.view.*

open class NewsFeedsAdapter(private val items : ArrayList<Article>, private val context: Context, private val clickListener: ClickListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_for_news_feeds, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsHeadline.text = removeSourceFromHeadline(items[position].title)
        holder.newsHeadlineImage.load(items[position].urlToImage)
        holder.newsSource.text = items[position].source!!.name
        holder.newsPublishedDate.text = parseServerDate(items[position].publishedAt)!!.toString()

        holder.mainLayoutNewsFeeds.setOnClickListener { clickListener.onRowClick(items[position], view = holder.mainLayoutNewsFeeds) }
    }

    interface ClickListener{
        fun onRowClick(article: Article, view: View )
    }
}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val newsHeadline = view.text_view_news_headline!!
    val newsHeadlineImage = view.img_view_headline_img!!
    val newsSource = view.text_view_source!!
    val newsPublishedDate = view.text_view_published_date!!
    val mainLayoutNewsFeeds = view.constraint_layout_news_feeds!!
}