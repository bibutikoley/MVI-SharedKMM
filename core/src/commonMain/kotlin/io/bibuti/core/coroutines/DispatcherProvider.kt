package io.bibuti.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    fun backgroundDispatcher(): CoroutineDispatcher = Dispatchers.Default
}