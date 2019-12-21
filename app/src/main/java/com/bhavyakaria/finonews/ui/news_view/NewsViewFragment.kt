package com.bhavyakaria.finonews.ui.news_view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load

import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.ui.news_feeds.NewsFeedsFragment
import com.bhavyakaria.finonews.utils.extensions.parseServerDate
import kotlinx.android.synthetic.main.fragment_news_view.*

/**
 * A simple [Fragment] subclass.
 */
class NewsViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_news_view, container, false)

        val article = arguments!!.getParcelable<Article>("article")
        val activity = activity as Context

        val img = view.findViewById<ImageView>(R.id.img_view_display_cover)

        img.load(article!!.urlToImage)

        setContent(article, view)

        return view
    }

    private fun setContent(article: Article, view: View) {
        view.findViewById<TextView>(R.id.text_view_news_headline).text = article.title
        view.findViewById<TextView>(R.id.text_view_published_date).text = parseServerDate(article.publishedAt)
        view.findViewById<TextView>(R.id.text_view_content).text = article.content
    }

    companion object {
        fun newInstance() = NewsViewFragment()
    }


}
