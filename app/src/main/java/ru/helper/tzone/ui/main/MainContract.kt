package ru.helper.tzone.ui.main

import ru.helper.tzone.mvp.BasePresenter
import ru.helper.tzone.mvp.BaseView
import ru.helper.tzone.network.model.News
import android.graphics.Movie
import retrofit2.Response
import ru.helper.tzone.network.model.ArticlesItem


object MainContract {
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun setAdapter(newsAdapter: NewsAdapter?)
        fun onResponseFailure(throwable: Throwable)
        fun startDetailActivity(articlesItem: ArticlesItem)
        fun showError(message: String)
        fun createAdapter(articlesItems: List<ArticlesItem>)
    }

    interface Presenter : BasePresenter<View> {
        fun requestDataFromServer()
        fun getAdapterListner(): NewsAdapter.Listener
        interface OnFinishedListener {
            fun onFinished(news: News)
            fun onFailure(t: Throwable)
            fun onFailure(responseError: String)
        }
    }

    interface Model {
        fun getNewsList(onFinishedListener: Presenter.OnFinishedListener)
    }
}