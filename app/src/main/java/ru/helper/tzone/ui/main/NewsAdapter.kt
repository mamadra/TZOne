package ru.helper.tzone.ui.main

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news_list.view.*
import ru.helper.tzone.R
import ru.helper.tzone.network.model.ArticlesItem
import ru.helper.tzone.ui.utils.GlideHelper
import java.util.ArrayList

class NewsAdapter(internal var listener: Listener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var articlesItems: SparseArray<ArticlesItem> = SparseArray<ArticlesItem>()
    private val mListener = listener

    interface Listener {
        fun onClickListener(articlesItem: ArticlesItem)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_news_list, p0, false)
        var viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            mListener.onClickListener(articlesItems.get(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return articlesItems.size()
    }

    override fun onBindViewHolder(p0: NewsViewHolder, p1: Int) {
        p0.bind(articlesItems[p1])
    }

    fun setItems(items: List<ArticlesItem>) {
        articlesItems.clear()
        items.forEachIndexed { index, articlesItem ->
            articlesItems.put(index, articlesItem)
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var newsPhoto: AppCompatImageView
        internal var newsName: AppCompatTextView

        init {
            newsPhoto = itemView.iv_news
            newsName = itemView.tv_news_title
        }

        fun bind(articlesItem: ArticlesItem) {
            newsName.text = articlesItem.title
            if (articlesItem.urlToImage.isNullOrEmpty()) {
                newsPhoto.setImageResource(R.drawable.placeholder)
            } else {
                articlesItem.urlToImage?.let {
                    GlideHelper(newsPhoto, it).setImageInImageView()
                }
            }
        }
    }
}