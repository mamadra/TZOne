package ru.helper.tzone.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_news.*
import ru.helper.tzone.R
import ru.helper.tzone.network.model.ArticlesItem
import ru.helper.tzone.ui.utils.GlideHelper

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var articlesItem: ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        articlesItem = intent.getParcelableExtra(getString(R.string.ARTICLES_ITEM_KEY))
        initUI()
    }

    private fun initUI() {
        if (::articlesItem.isInitialized) {
            if (articlesItem.urlToImage.isNullOrEmpty()) {
                iv_news.setImageResource(R.drawable.placeholder)
            } else {
                articlesItem.urlToImage?.let {
                    GlideHelper(iv_news, it).setImageInImageView()
                }
            }
            tv_news_title.text = articlesItem.title
            tv_news_discription.text = articlesItem.description
            tv_autor.text = articlesItem.author
        }
    }
}