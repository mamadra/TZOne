package ru.helper.tzone.mvp


interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}