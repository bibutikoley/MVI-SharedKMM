package io.bibuti.samplemvi.news

import io.bibuti.samplemvi.models.NewsArticle
import io.bibuti.samplemvi.models.NewsType

sealed class NewsActions {
    data class FetchNewsArticles(val type: NewsType) : NewsActions()
    data class SaveNewsArticles(val news: List<NewsArticle>) : NewsActions()
}