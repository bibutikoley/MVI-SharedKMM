package io.bibuti.samplemvi.network

import io.bibuti.core.components.ThreadInfo
import io.bibuti.samplemvi.models.NewsArticle
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json

class NewsClient(private val threadInfo: ThreadInfo) {

    private val baseUrl = "https://newsapi.org/v2"
    private val apiKey: String = "8538424f30a44aff929ea0a6eb8cd575"

    private val contentType = "application/json;charset=utf-8"

    private val httpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            io.ktor.client.features.observer.ResponseObserver {
                println("ResponseObserver HTTP -> $it")
            }
        }
    }

    fun dispose() {
        httpClient.close()
    }

    suspend fun fetchTopHeadlines(): List<NewsArticle> {
        val scope = CoroutineScope(Dispatchers.Default)
        val newsArticles = scope.async {
            if (threadInfo.isMainThread()) {
                println("WARNING: RUNNING QUERY ON MAIN THREAD")
            }

            val newsResponse =
                httpClient.get<FetchNewsResponse>("${baseUrl}/top-headlines?country=us") {
                    baseConfiguration()
                }
            newsResponse.articles?.map { article ->
                NewsArticle(title = article.title ?: "", description = article.description ?: "")
            }
        }
        return newsArticles.await() ?: emptyList()
    }

    suspend fun fetchEverything(query: String): List<NewsArticle> {
        val scope = CoroutineScope(Dispatchers.Default)
        val newsArticles = scope.async {
            if (threadInfo.isMainThread()) {
                println("WARNING: RUNNING QUERY ON MAIN THREAD")
            }

            val newsResponse =
                httpClient.get<FetchNewsResponse>("${baseUrl}/everything?q=$query") {
                    baseConfiguration()
                }
            newsResponse.articles?.map { article ->
                NewsArticle(title = article.title ?: "", description = article.description ?: "")
            }
        }
        return newsArticles.await() ?: emptyList()
    }

    private fun HttpRequestBuilder.baseConfiguration() {
        headers {
            append("Accept", contentType)
            append("X-Api-Key", apiKey)
        }
    }
}