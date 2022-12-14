package io.bibuti.samplemvi.models

sealed class NewsType(val value: String) {
    object TopHeadlines : NewsType("top-headlines")
    data class Everything(val query: String) : NewsType("everything")
}