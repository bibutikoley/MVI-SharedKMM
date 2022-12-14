package io.bibuti.samplemvi.news

import io.bibuti.core.coroutines.DispatcherProvider
import io.bibuti.core.interfaces.SideEffectInterface
import io.bibuti.samplemvi.models.NewsType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

sealed class NewsEffects(
    override val dispatcher: CoroutineDispatcher = DispatcherProvider.backgroundDispatcher(),
    override val coroutineScope: CoroutineScope? = null
) : SideEffectInterface {
    data class LoadNewsArticles(val type: NewsType) : NewsEffects()
}