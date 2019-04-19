package ru.helper.tzone.ui.main

import retrofit2.Response
import ru.helper.tzone.mvp.BasePresenterImpl
import ru.helper.tzone.network.model.ArticlesItem
import ru.helper.tzone.network.model.News


class MainPresenter() : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

    interface OnModelFinishedListener : MainContract.Presenter.OnFinishedListener {
        override fun onFinished(news: News)
        override fun onFailure(responseError: String)
        override fun onFailure(t: Throwable)
    }

    private lateinit var mModel: MainContract.Model

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        mModel = MainRepo()
    }

    override fun detachView() {
        super.detachView()
    }

    override fun requestDataFromServer() {
        mModel.getNewsList(modelListener)
    }

    private val modelListener = object : OnModelFinishedListener {
        override fun onFailure(responseError: String) {
            mView?.hideProgress()
            mView?.showError(responseError)
        }

        override fun onFailure(t: Throwable) {
            mView?.hideProgress()
            mView?.onResponseFailure(t)
        }

        override fun onFinished(news: News) {
            mView?.hideProgress()
            news.let {
                mView?.createAdapter(it.articles as List<ArticlesItem>)
            }
        }
    }

    private val adapterListner = object : NewsAdapter.Listener {
        override fun onClickListener(articlesItem: ArticlesItem) {
            mView?.startDetailActivity(articlesItem)
        }
    }

    override fun getAdapterListner(): NewsAdapter.Listener {
        return adapterListner
    }
}