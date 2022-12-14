package io.bibuti.core.components

import io.bibuti.core.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(DispatcherProvider.mainDispatcher() + SupervisorJob())
}