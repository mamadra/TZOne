package ru.helper.tzone.ui.main

import ru.helper.tzone.network.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.helper.tzone.network.ApiClient


class MainRepo : MainContract.Model {

    private val API_KEY = "9a9eb07fe6fd4f268d7eb59eea027b4d"
    private val SOURCES = "ansa"

    override fun getNewsList(onFinishedListener: MainContract.Presenter.OnFinishedListener) {
        val call = ApiClient.mApiService.getNews(API_KEY, SOURCES)
        call.enqueue(object : Callback<News?> {
            override fun onFailure(call: Call<News?>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                if (response.isSuccessful) {
                    val news = response.body()
                    onFinishedListener.onFinished(news!!)
                } else {
                    onFinishedListener.onFailure(response.message())
                }
            }
        })
    }
}