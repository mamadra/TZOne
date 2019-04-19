package ru.helper.tzone.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.helper.tzone.mvp.BaseActivity
import android.widget.ProgressBar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import ru.helper.tzone.R
import ru.helper.tzone.network.model.ArticlesItem
import ru.helper.tzone.ui.detail.DetailNewsActivity


class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override var mPresenter: MainContract.Presenter = MainPresenter()
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        getNewsList()
    }

    private fun initUI() {
        progressBar = progress
        recyclerView = rv_news_list
    }

    private fun getNewsList() {
        if (newsAdapter == null)
            mPresenter.requestDataFromServer()
    }

    override fun getCtx(): Activity {
        return this
    }

    override fun showProgress() {
        progressBar!!.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }

    override fun onResponseFailure(throwable: Throwable) {
        showToastError(throwable.localizedMessage)
    }

    override fun setAdapter(newsAdapter: NewsAdapter?) {
        recyclerView!!.layoutManager = LinearLayoutManager(getCtx())
        recyclerView!!.adapter = newsAdapter
    }

    override fun startDetailActivity(articlesItem: ArticlesItem) {
        var intent = Intent(this, DetailNewsActivity::class.java)
        intent.putExtra(getString(R.string.ARTICLES_ITEM_KEY), articlesItem)
        startActivities(arrayOf(intent))
    }

    override fun showError(message: String) {
        showToastError(message)
    }

    override fun createAdapter(articlesItems: List<ArticlesItem>) {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(mPresenter.getAdapterListner())
        }
        newsAdapter?.setItems(articlesItems)
        setAdapter(newsAdapter)
    }

    private fun showToastError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}