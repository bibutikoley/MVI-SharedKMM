package io.bibuti.samplemvi.news

import io.bibuti.core.components.ThreadInfo
import io.bibuti.core.interfaces.Processor
import io.bibuti.samplemvi.models.NewsType
import io.bibuti.samplemvi.network.NewsClient

class NewsProcessor(threadInfo: ThreadInfo) : Processor<NewsEffects, NewsActions> {

    //initialize api
    private val newsClient = NewsClient(threadInfo = threadInfo)

    override suspend fun dispatchSideEffect(effect: NewsEffects): NewsActions {
        return when (effect) {
            is NewsEffects.LoadNewsArticles -> fetchNewsArticles(effect)
        }
    }

    private suspend fun fetchNewsArticles(effect: NewsEffects.LoadNewsArticles): NewsActions {
        val headlines = when (effect.type) {
            is NewsType.TopHeadlines -> newsClient.fetchTopHeadlines()
            is NewsType.Everything -> newsClient.fetchEverything(query = effect.type.query)
        }
        return NewsActions.SaveNewsArticles(news = headlines)
    }

}