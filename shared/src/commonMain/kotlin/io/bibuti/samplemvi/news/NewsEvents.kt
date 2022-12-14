package io.bibuti.samplemvi.news

import io.bibuti.samplemvi.models.NewsArticle

sealed class NewsEvents {
    data class OpenSelectedNews(val newsArticle: NewsArticle)
}