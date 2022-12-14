package io.bibuti.samplemvi.news

import io.bibuti.core.components.Next
import io.bibuti.core.interfaces.Updater


typealias NewsResult = Next<NewsState, NewsEffects, NewsEvents>

class NewsUpdater : Updater<NewsActions, NewsState, NewsEffects, NewsEvents> {
    override fun onNewAction(
        action: NewsActions,
        currentState: NewsState,
    ): Next<NewsState, NewsEffects, NewsEvents> {
        return when (action) {
            is NewsActions.FetchNewsArticles -> fetchNews(action, currentState)
            is NewsActions.SaveNewsArticles -> saveNews(action, currentState)
        }
    }

    private fun saveNews(
        action: NewsActions.SaveNewsArticles,
        currentState: NewsState,
    ): Next<NewsState, NewsEffects, NewsEvents> {
        return Next.State(currentState.copy(newsArticleList = action.news))
    }

    private fun fetchNews(
        action: NewsActions.FetchNewsArticles,
        currentState: NewsState,
    ): NewsResult {
        return Next.StateWithSideEffects(
            state = currentState,
            sideEffects = setOf(NewsEffects.LoadNewsArticles(type = action.type)),
        )
    }
}
