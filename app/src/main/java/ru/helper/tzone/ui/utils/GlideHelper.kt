package ru.helper.tzone.ui.utils

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import com.bumptech.glide.Glide
import ru.helper.tzone.R

class GlideHelper(iv: AppCompatImageView, url: String) {

    private val iv = iv
    private val strUrl = url

    fun setImageInImageView() {
        Glide.with(iv.context)
            .load(strUrl)
            .placeholder(R.drawable.placeholder)
            .into(iv)
    }
}