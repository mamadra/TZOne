package ru.helper.tzone.mvp

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager


abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {

    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}