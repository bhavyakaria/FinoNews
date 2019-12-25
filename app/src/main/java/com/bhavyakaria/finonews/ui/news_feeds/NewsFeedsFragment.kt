package com.bhavyakaria.finonews.ui.news_feeds

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhavyakaria.finonews.R
import com.bhavyakaria.finonews.models.Article
import com.bhavyakaria.finonews.ui.news_view.NewsViewFragment
import com.bhavyakaria.finonews.utils.extensions.toast

class NewsFeedsFragment : Fragment(), NewsFeedsAdapter.ClickListener {

    companion object {
        fun newInstance() = NewsFeedsFragment()
    }

    private lateinit var viewModel: NewsFeedsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val articles = arguments!!.getParcelableArrayList<Article>("newsFeedsList")

        val view : View = inflater.inflate(R.layout.news_feeds_fragment, container, false)
        val activity =activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_news_feeds)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        activity.supportActionBar!!.elevation = 6.0f

        setRecyclerView(recyclerView, articles, activity)
        return view
    }

    private fun setRecyclerView(recyclerView: RecyclerView, articles: ArrayList<Article>?, activity: Context) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = NewsFeedsAdapter(articles!!, activity, this)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    fun updateNewsFeeds(article: Article) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsFeedsViewModel::class.java)
    }

    override fun onRowClick(article: Article, view: View) {

        context?.let { toast(it, article.title) }

        val bundle = Bundle()
        bundle.putParcelable("article", article)

        val fragment : NewsViewFragment = NewsViewFragment.newInstance()
        fragment.arguments = bundle

        val ft = fragmentManager!!.beginTransaction()
        ft.replace(R.id.news_feeds_fragment, fragment)
        ft.commit()

    }


}
