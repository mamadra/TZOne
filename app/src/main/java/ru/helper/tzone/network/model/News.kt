package ru.helper.tzone.network.model

data class News(
    val totalResults: Int? = null,
    val articles: List<ArticlesItem?>? = null,
    val status: String? = null
)
