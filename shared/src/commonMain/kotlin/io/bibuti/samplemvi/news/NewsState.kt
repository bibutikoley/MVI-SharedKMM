package io.bibuti.samplemvi.news

import io.bibuti.samplemvi.models.NewsArticle

data class NewsState(
    val newsArticleList: List<NewsArticle> = emptyList(),
    val selectedNewsArticle: NewsArticle? = null
)