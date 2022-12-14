package io.bibuti.samplemvi.news

import io.bibuti.core.components.ArchViewModel
import io.bibuti.core.components.ThreadInfo
import io.bibuti.samplemvi.models.NewsType
import kotlinx.coroutines.CoroutineExceptionHandler

class NewsViewModel(
    threadInfo: ThreadInfo,
) : ArchViewModel<NewsActions, NewsState, NewsEffects, NewsEvents, Nothing>(
    updater = NewsUpdater(),
    initialState = NewsState(),
    threadInfo = threadInfo,
    initialEffects = setOf(NewsEffects.LoadNewsArticles(type = NewsType.TopHeadlines)),
    processor = NewsProcessor(threadInfo = threadInfo),
    coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error -> $throwable")
    }
)